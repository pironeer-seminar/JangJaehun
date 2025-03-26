package com.example.ordersystem.dto;

import com.example.ordersystem.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUserDto {
    private String name;
    private String email;

    // 새로운 User 등록
    public User toUser() {
        return new User(name, email);
    }
}
