package com.example.pironeer.controller;

import com.example.pironeer.dto.request.CommentCreateReq;
import com.example.pironeer.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor // final로 선언된 객체의 생성자를 자동으로 생성
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}/{userId}")
    public String createComment(
            @PathVariable("postId") Long postId, // 2번째 게시물에 대해선 댓글이 달리지 않고 에러 발생
            @PathVariable("userId") Long userId,
            @RequestBody CommentCreateReq req) {
        return commentService.create(req, postId, userId);
    }

}
