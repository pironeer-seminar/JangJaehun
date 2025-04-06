package com.example.demo1.post.controller;

import com.example.demo1.common.dto.ApiRes;
import com.example.demo1.common.type.PostSuccessType;
import com.example.demo1.post.dto.request.PostCreateReq;
import com.example.demo1.post.dto.request.PostUpdateReq;
import com.example.demo1.post.dto.response.PostSearchRes;
import com.example.demo1.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Post", description = "게시글 관련 API") // Swagger 문서에서 API 그룹핑
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 생성
    @PostMapping("")
    @Operation(summary = "게시글 생성", description = "게시글을 생성합니다.") // Swagger에서 API 메서드 설명
    @ApiResponse(responseCode = "200", description = "게시글 생성에 성공하였습니다!") // Swagger에서 Api 응답 설명
//    public Long create(@RequestBody PostCreateReq req) {
//        return postService.create(req);
//    }
    public ApiRes<?> create(
            @Valid // 유효성 검증 트리거 설정
            @RequestBody PostCreateReq req
    ) {
        postService.create(req);

        return ApiRes.success(PostSuccessType.CREATE);
    }

    // 목록조회
    @GetMapping("")
    @Operation(summary = "게시글 목록 조회", description = "공개 여부가 PUBLIC인 게시글 전체 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "게시글 목록이 성공적으로 조회되었습니다.")
//    public List<PostSearchRes> search() {
//        return postService.search();
//    }
    public ApiRes<List<PostSearchRes>> search() {
        return ApiRes.success(PostSuccessType.GET_ALL, postService.search());
    }

    // 단일조회
    @GetMapping("/{postId}")
    @Operation(summary = "게시글 상세 조회", description = "게시글 상세 내용을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "게시글 상세 조회에 성공하였습니다.")
    @ApiResponse(responseCode = "404", description = "게시글 ID와 일치하는 게시글이 없습니다.")
//    public PostSearchRes detail(
//            @PathVariable("postId") Long postId
//    ) {
//        return postService.detail(postId);
//    }
    public ApiRes<PostSearchRes> detail( // post의 response Dto를 ApiRes에 담아서 return!
            @Parameter(description = "게시글 ID", example = "1") // Swagger에서 단일 파라미터의 설명 추가
            @PathVariable("postId") Long postId // 경로로 변수를 받아 postId라는 매개변수로 전달
    ) {
        return ApiRes.success(PostSuccessType.GET_DETAIL, postService.detail(postId)); // ApiRes의 success 함수에 GET_DETAIL을 인자 1, postService의 detail 함수의 return을 인자 2로 전달
    }

    // 수정
    @PutMapping("/{postId}")
    @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
    @ApiResponse(responseCode = "200", description = "게시글이 성공적으로 수정되었습니다.")
    @ApiResponse(responseCode = "404", description = "게시글 ID와 일치하는 게시글이 없습니다.")
//    public Long update(
//            @PathVariable("postId") Long postId,
//            @RequestBody PostUpdateReq req) {
//        return postService.update(postId, req);
//    }
    public ApiRes<?> update(
            @Parameter(description = "게시글 ID", example = "1")
            @PathVariable("postId") Long postId,
            @Valid // 유효성 검증 트리거 설정
            @RequestBody PostUpdateReq req
    ) {
        postService.update(postId, req); // 비즈니스 로직을 이용해 실제 값 update

        return ApiRes.success(PostSuccessType.UPDATE); // 이 경우 data 없이 return을 한 이유는 postService.update의 return 값이 단순 postId 값이기에 의미가 없는 데이터라 생각했기 때문
    }

    // 삭제
    @DeleteMapping("/{postId}")
    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "게시글이 성공적으로 삭제되었습니다.")
//    public Long delete(
//            @PathVariable("postId") Long postId
//    ) {
//        return postService.delete(postId);
//    }
    public ApiRes<?> dalete(
            @Parameter(description = "게시글 ID", example = "1")
            @PathVariable("postId") Long postId
    ) {

        postService.delete(postId);
        return ApiRes.success(PostSuccessType.DELETE);

    }
}
