package com.example.demo1.post.controller;

import com.example.demo1.common.dto.ApiRes;
import com.example.demo1.common.type.PostSuccessType;
import com.example.demo1.post.dto.request.PostCreateReq;
import com.example.demo1.post.dto.request.PostUpdateReq;
import com.example.demo1.post.dto.response.PostSearchRes;
import com.example.demo1.post.service.PostService;
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
//    public Long create(@RequestBody PostCreateReq req) {
//        return postService.create(req);
//    }
    public ApiRes<?> create(@RequestBody PostCreateReq req) {
        postService.create(req);

        return ApiRes.success(PostSuccessType.CREATE);
    }

    // 목록조회
    @GetMapping("")
//    public List<PostSearchRes> search() {
//        return postService.search();
//    }
    public ApiRes<List<PostSearchRes>> search() {
        return ApiRes.success(PostSuccessType.GET_ALL, postService.search());
    }

    // 단일조회
    @GetMapping("/{postId}")
//    public PostSearchRes detail(
//            @PathVariable("postId") Long postId
//    ) {
//        return postService.detail(postId);
//    }
    public ApiRes<PostSearchRes> detail( // post의 response Dto를 ApiRes에 담아서 return!
            @PathVariable("postId") Long postId // 경로로 변수를 받아 postId라는 매개변수로 전달
    ) {
        return ApiRes.success(PostSuccessType.GET_DETAIL, postService.detail(postId)); // ApiRes의 success 함수에 GET_DETAIL을 인자 1, postService의 detail 함수의 return을 인자 2로 전달
    }

    // 수정
    @PutMapping("/{postId}")
//    public Long update(
//            @PathVariable("postId") Long postId,
//            @RequestBody PostUpdateReq req) {
//        return postService.update(postId, req);
//    }
    public ApiRes<?> update(
            @PathVariable("postId") Long postId,
            @RequestBody PostUpdateReq req
    ) {
        postService.update(postId, req); // 비즈니스 로직을 이용해 실제 값 update

        return ApiRes.success(PostSuccessType.UPDATE); // 이 경우 data 없이 return을 한 이유는 postService.update의 return 값이 단순 postId 값이기에 의미가 없는 데이터라 생각했기 때문
    }

    // 삭제
    @DeleteMapping("/{postId}")
//    public Long delete(
//            @PathVariable("postId") Long postId
//    ) {
//        return postService.delete(postId);
//    }
    public ApiRes<?> dalete(
            @PathVariable("postId") Long postId
    ) {

        postService.delete(postId);
        return ApiRes.success(PostSuccessType.DELETE);

    }
}
