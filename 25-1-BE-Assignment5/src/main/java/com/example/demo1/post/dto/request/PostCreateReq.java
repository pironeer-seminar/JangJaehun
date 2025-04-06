package com.example.demo1.post.dto.request;

import com.example.demo1.post.entity.PostStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class PostCreateReq {

    @Schema(description = "작성자 ID", example = "1") // Swagger에서 DTO 내의 각 필드 설명
    private Long userId;

    @Schema(description = "게시글 제목", example = "Swagger")
    private String title;

    @Schema(description = "게시글 내용", example = "Swagger에 대한 설명")
    private String content;

    @Schema(description = "공개 여부", example = "PUBLIC")
    private PostStatus status;
}
