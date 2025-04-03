package com.example.pironeer.controller;

import com.example.pironeer.dto.request.CommentCreateReq;
import com.example.pironeer.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor // final로 선언된 객체의 생성자를 자동으로 생성
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}/{userId}")
    public String createComment(
            @PathVariable("postId") Long postId,
            @PathVariable("postId") Long userId,
            @RequestBody CommentCreateReq req) {
        return commentService.create(req, postId, userId);
    }

}
