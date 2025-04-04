package com.example.pironeer.dto.request;

import lombok.Getter;

@Getter
public class CommentCreateReq {

//    private Long userId; // 이미 PathVariable로 값을 받고 있으므로 선언 필요 x
//
//    private Long postId; // 이미 PathVariable로 값을 받고 있으므로 선언 필요 x

    private String content;
}
