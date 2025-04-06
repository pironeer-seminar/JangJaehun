package com.example.pironeer.repository;

import com.example.pironeer.entity.Post;
import com.example.pironeer.entity.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // PostService.search에서 사용할 status가 PUBLIC인 게시글만 조회할 수 있는 메서드를 선언
    List<Post> findAllByStatus(PostStatus status);

    List<Post> findAllByUserId(Long userId);
}
