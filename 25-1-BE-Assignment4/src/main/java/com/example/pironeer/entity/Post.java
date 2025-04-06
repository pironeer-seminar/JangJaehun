package com.example.pironeer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT") // TEXT로 지정하기 위해 columnDefinition 속성에서 지정
    private String content;

    @Enumerated(EnumType.STRING) // Enum 값을 DB에 적용하기 위해서 필요한 어노테이션, STRING은 문자열로 저장하는 것이고 ORDINAL은 숫자로 저장
    @Column(nullable = false, length = 100)
    private PostStatus status;

    @Column(nullable = false, updatable = false) // updatable = false : 수정불가하게 함
    private LocalDateTime createdAt;


    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    // 댓글을 함께 조회할 수 있도록 OneToMany 관계로 작성
    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    // builder를 이용해 create 메서드 생성
    public static Post create(User author, String title, String content, PostStatus status) {
        return Post.builder()
                .user(author)
                .title(title)
                .content(content)
                .status(status)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public void update(String title, String content, PostStatus status) {
        this.title = title;
        this.content = content;
        this.status = status;
    }
}
