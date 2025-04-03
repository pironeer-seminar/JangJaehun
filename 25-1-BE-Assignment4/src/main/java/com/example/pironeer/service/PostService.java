package com.example.pironeer.service;

import com.example.pironeer.dto.request.PostCreateReq;
import com.example.pironeer.dto.request.PostUpdateReq;
import com.example.pironeer.dto.response.PostSearchRes;
import com.example.pironeer.entity.Post;
import com.example.pironeer.entity.PostStatus;
import com.example.pironeer.entity.User;
import com.example.pironeer.repository.PostRepository;
import com.example.pironeer.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository; // 요청 값으로 userId 값을 받아왔기에 이 값으로 DB에서 조회를 해야함

    public Long create(PostCreateReq req) {
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("조회한 유저가 없습니다"));

        Post post = Post.create(user, req.getTitle(), req.getContent(), req.getStatus());
        postRepository.save(post); // post 값을 DB에 저장

        return post.getId(); // 저장된 post의 id 값을 반환
    }

    public List<PostSearchRes> search() {
        // 제약 조건: PostStatus가 PUBLIC인 게시글만 조회할 수 있다!
        List<Post> posts = postRepository.findAllByStatus(PostStatus.PUBLIC);

        // Stream API를 사용해 PostSearchRes라는 응답 DTO로 변환
        return posts.stream()
                // map을 이용해 Post를 PostSearchRes로 변환!
                .map(post -> new PostSearchRes(post.getUser().getId(), post.getId(), post.getTitle(), post.getContent(), post.getCreatedAt()))
                .toList();
    }

    public PostSearchRes detail(Long postId) {
        // 게시글 레포지토리(DB)에서 조회
        Post post = postRepository.findById(postId) // 이렇게까지 하면 Optional로 반환되기에 예외처리 필요
                .orElseThrow(() -> new IllegalArgumentException("조회된 게시글이 없습니다."));
        return new PostSearchRes(post.getUser().getId(), post.getId(), post.getTitle(), post.getContent(), post.getCreatedAt());

    }

    public Long update(Long postId, PostUpdateReq req) {
        Post post = postRepository.findById(postId) // Post 객체를 조회해서 Id 값에 해당하는 객체를 가지고 옴
                .orElseThrow(() -> new IllegalArgumentException("조회된 게시글이 없습니다."));

        post.update(req.getTitle(), req.getContent(), req.getStatus()); // 조회된 게시글을 update
        postRepository.save(post); // update된 값 저장

        return post.getId();
    }

    public Long delete(Long postId) {

        postRepository.deleteById(postId); // 레포지토리에 자동 선언되어있는 메서드를 사용
        return postId; // 기존에 들어왔던 post의 id 값을 반환해 해당 post가 삭제되었다고 알림
    }

    public List<PostSearchRes> getUserPosts(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("조회된 사용자가 없습니다."));

        List<Post> posts = postRepository.findAllByUserId(user.getId());

        return posts.stream()
                .map(post -> new PostSearchRes(userId, post.getId(), post.getTitle(), post.getContent(), post.getCreatedAt()))
                .toList();
    }
}
