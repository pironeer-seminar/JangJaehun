package com.example.demo1.user.controller;

import com.example.demo1.common.dto.ApiRes;
import com.example.demo1.common.type.UserSuccessType;
import com.example.demo1.user.dto.request.UserCreateReq;
import com.example.demo1.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "유저 관련 API")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("")
    @Operation(summary = "유저 생성", description = "새로운 유저를 생성합니다.")
    @ApiResponse(responseCode = "200", description = "성공적으로 유저를 생성하였습니다.")
//    public Long create(@RequestBody UserCreateReq req) {
//        return userService.create(req);
//    }
    public ApiRes<?> create(@RequestBody UserCreateReq userCreateReq) {

        userService.create(userCreateReq); // 값을 DB에 저장

        return ApiRes.success(UserSuccessType.CREATE);
    }

}
