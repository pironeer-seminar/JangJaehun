package com.example.pironeer.repository;

import com.example.pironeer.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    // userId와 postId가 일치하는 데이터 조회
    List<Like> findByUserIdAndPostId(Long userId, Long postId); // 반드시 findBy"A"And"B"로 작성해야 올바른 쿼리문이 돌아감!
    
}