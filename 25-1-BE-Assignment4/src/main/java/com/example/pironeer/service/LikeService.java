package com.example.pironeer.service;

import com.example.pironeer.entity.Like;
import com.example.pironeer.entity.Post;
import com.example.pironeer.entity.User;
import com.example.pironeer.repository.LikeRepository;
import com.example.pironeer.repository.PostRepository;
import com.example.pironeer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public boolean like(Long postId, Long userId) {

        // userId과 postId를 가지고 와서 해당 유저가 post에 좋아요를 했는지 여부 확인
        User user = userRepository.findById(userId) // optional 이기에 반드시 예외처리!
                .orElseThrow(() -> new IllegalArgumentException("조회된 유저가 없습니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("조회된 게시글이 없습니다."));

        // 모든 좋아요를 조회하고 일치하는 user와 post를 찾기보단 repository에 메서드를 생성
        List<Like> likes = likeRepository.findByUserIdAndPostId(user.getId(), post.getId());

        if (!likes.isEmpty()) { // 해당 요소가 비어있다면

            likeRepository.delete(likes.get(0));

            return false;
        } else {
            Like like = Like.create(user, post);

            likeRepository.save(like); // 실제 DB에 저장

            return true;
        }

    }
}
