package com.example.pironeer.dto.user;

import com.example.pironeer.domain.User;

import java.util.List;
import java.util.stream.Collectors;

// 전체 유저 목록 조회
public class ResponseUserListDto {
    private List<ResponseUserIdDto> users;

    public ResponseUserListDto(List<User> userList){
        this.users = userList.stream()
                .map(ResponseUserIdDto::new)
                .collect(Collectors.toList());
    }

    public List<ResponseUserIdDto> getUsers() {
        return users;
    }
}
