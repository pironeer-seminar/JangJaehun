package com.example.demo1.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserCreateReq {

    @Schema(description = "유저명", example = "User")
    @NotNull(message = "유저명은 필수입니다.") // 유효성 검사
    @Size(min = 1, max = 20) // 유효성 검사
    private String name;
}
