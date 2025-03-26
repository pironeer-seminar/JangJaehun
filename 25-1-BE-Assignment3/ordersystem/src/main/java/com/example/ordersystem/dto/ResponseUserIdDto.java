package com.example.ordersystem.dto;

import com.example.ordersystem.domain.User;
import lombok.Getter;

@Getter
public class ResponseUserIdDto {
    private String name;
    private String email;

    public ResponseUserIdDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }
}