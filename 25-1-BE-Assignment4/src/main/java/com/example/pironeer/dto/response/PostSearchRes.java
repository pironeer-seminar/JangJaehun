package com.example.pironeer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class PostSearchRes {

    // 반환할 필드들 생성
    private Long userId;
    private Long postId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private List<CommentSearchRes> comments; // comment를 DTO 형태로 저장

}
