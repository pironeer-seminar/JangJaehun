package com.example.pironeer.controller;

import com.example.pironeer.dto.request.PostCreateReq;
import com.example.pironeer.dto.request.PostUpdateReq;
import com.example.pironeer.dto.response.PostSearchRes;
import com.example.pironeer.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 생성
    @PostMapping("")
    public Long create(@RequestBody PostCreateReq req) {
        return postService.create(req);
    }

    // 목록조회 -> List를 받아와야 함
    // PostSearchRes DTO는 하나의 객체만 의미하는 것이기에 리스트로 반환
    @GetMapping("")
    public List<PostSearchRes> search() {
        return postService.search();
    }

    // 단일조회
    @GetMapping("/{postId}") // postId라는 경로변수 사용
    public PostSearchRes detail(@PathVariable("postId") Long postId) { // @PathVariable : 경로변수의 값을 가지고 옴
        return postService.detail(postId);
    }

    // 수정
    @PutMapping("/{postId}")
    public Long update(
            @PathVariable("postId") Long postId,
            @RequestBody PostUpdateReq req) { // 어떤 값으로 수정할 것인지 알아야 하기에 RequestBody 사용
        return postService.update(postId, req);
    }

    // 삭제
    @DeleteMapping("/{postId}")
    public Long delete(@PathVariable("postId") Long postId) { // 경로 변수로 값을 받기에 @PathVariable 사용
        return postService.delete(postId); // 삭제된 id 값 반환
    }

    // 유저가 만든 게시글 목록 조회
    @GetMapping("/user/{userId}")
    public List<PostSearchRes> getUserPosts(@PathVariable("userId") Long userId) {
        return postService.getUserPosts(userId);
    }
}
