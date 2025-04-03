package com.example.pironeer.controller;

import com.example.pironeer.dto.request.UserCreateReq;
import com.example.pironeer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor // final이 붙은 필드들에 대해서 자동으로 생성자 생성
public class UserController {

    // Service를 Controller에서 사용하기 위해 생성자 주입 방식으로 Service 선언
    private final UserService userService;

//    @RequiredArgsController로 인해 생성되는 생성자
//    public UserController(UserService userService){
//        this.userService = userService;
//    }

//    @PostMapping("")
//    public UserCreateRes create(@RequestBody UserCreateReq req) {
//        return userService.create(req);
//    }

    @PostMapping("")
    public Long create(@RequestBody UserCreateReq req) { // 편의를 위해 Long type(생성된 객체의 id 값)으로 반환하도록 함
        return userService.create(req);
    }

}
