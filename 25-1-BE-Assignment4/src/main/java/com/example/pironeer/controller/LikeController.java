package com.example.pironeer.controller;

import com.example.pironeer.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{postId}/{userId}")
    public boolean like(
            @PathVariable("postId") Long postId,
            @PathVariable("userId") Long userId
    ) {
        return likeService.like(postId, userId);
    }
}
