package com.example.demo1.post.dto.request;

import com.example.demo1.post.entity.PostStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PostCreateReq {

    @Schema(description = "작성자 ID", example = "1") // Swagger에서 DTO 내의 각 필드 설명
    @NotNull(message = "유저 ID는 필수입니다.")
    private Long userId;

    @NotBlank(message = "제목을 입력해주세요.")
    @Size(min = 1, max = 20, message = "제목은 1글자 이상 20글자 이하여야 합니다.")
    @Schema(description = "게시글 제목", example = "Swagger")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    @Schema(description = "게시글 내용", example = "Swagger에 대한 설명")
    private String content;

    @Schema(description = "공개 여부", example = "PUBLIC")
    private PostStatus status;
}
