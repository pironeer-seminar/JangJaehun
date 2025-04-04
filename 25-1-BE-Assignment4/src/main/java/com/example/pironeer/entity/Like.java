package com.example.pironeer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post_likes") // 기존 like라는 예약어가 있기에 명시적으로 table명 선언
@Getter
@Builder // builder를 이용해 엔티티 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    private boolean liked;

    // like 객체를 생성하는 메서드 (builder 이용)
    public static Like create(User user, Post post, boolean liked) {
        return Like.builder()
                .user(user)
                .post(post)
                .liked(liked)
                .build();
    }
}
