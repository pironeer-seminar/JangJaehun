package com.example.pironeer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentSearchRes {

    private Long userId;
    
    private String content;
}
