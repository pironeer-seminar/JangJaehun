package com.example.pironeer.dto.user;

import com.example.pironeer.domain.User;

// 새로운 User 생성 DTO
public class RequestUserDto {
    private String name;
    private String email;

    // 새로운 User 등록
    public User toUser() {
        return new User(name, email);
    }

    // 생성자
    public RequestUserDto() {} // 기본 생성자

    // Getter
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
