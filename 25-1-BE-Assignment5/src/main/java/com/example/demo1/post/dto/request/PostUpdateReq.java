package com.example.demo1.post.dto.request;

import com.example.demo1.post.entity.PostStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class PostUpdateReq {

    @Schema(description = "수정할 게시글 제목", example = "게시글 제목 수정")
    private String title;

    @Schema(description = "수정한 내용", example = "게시글 내용 수정")
    private String content;

    @Schema(description = "공개 여부", example = "PRIVATE")
    private PostStatus status;
}
