package com.example.pironeer.dto.user;

import com.example.pironeer.domain.User;

public class ResponseUserIdDto {
    private String name;
    private String email;

    public ResponseUserIdDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}