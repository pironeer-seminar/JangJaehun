package com.example.demo1.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostSearchRes {

    @Schema(description = "작성자 ID", example = "1")
    private Long userId;

    @Schema(description = "게시글 ID", example = "1")
    private Long postId;

    @Schema(description = "게시글 제목", example = "Swagger")
    private String title;

    @Schema(description = "게시글 내용", example = "Swagger에 대한 설명")
    private String content;

    @Schema(description = "게시글 생성 시각", example = "2025-04-06T11:08:01.446Z")
    private LocalDateTime createdAt;
}
