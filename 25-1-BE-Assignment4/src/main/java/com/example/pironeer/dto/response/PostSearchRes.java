package com.example.pironeer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostSearchRes {

    // 반환할 필드들 생성
    private Long userId;
    private Long postId;
    private String title;
    private String content;
    private LocalDateTime createdAt;

}
