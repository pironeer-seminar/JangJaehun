package com.example.demo1.post.dto.request;

import com.example.demo1.post.entity.PostStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class PostUpdateReq {

    @Schema(description = "수정할 게시글 제목", example = "게시글 제목 수정")
    @NotBlank(message = "수정할 제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "수정할 내용을 입력해주세요")
    @Schema(description = "수정한 내용", example = "게시글 내용 수정")
    private String content;

    @Pattern(regexp = "PUBLIC/PRIVATE", message = "status는 PUBLIC 혹은 PRIVATE 여야 합니다.")
    @Schema(description = "공개 여부", example = "PRIVATE")
    private PostStatus status;
}
