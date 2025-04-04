package com.example.pironeer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA가 내부적으로 Comment라는 객체를 관리하기 위해 PROTECTED로 선언
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    public static Comment create(User user, Post post, String content) { // 왜 static으로 선언하는가? 정적 팩터리 메서드?

        return Comment.builder()
                .user(user)
                .post(post)
                .content(content)
                .build();

    }

}
