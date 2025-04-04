package com.example.pironeer.service;

import com.example.pironeer.dto.request.CommentCreateReq;
import com.example.pironeer.entity.Comment;
import com.example.pironeer.entity.Post;
import com.example.pironeer.entity.User;
import com.example.pironeer.repository.CommentRepository;
import com.example.pironeer.repository.PostRepository;
import com.example.pironeer.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public Long create(CommentCreateReq req, Long postId, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("조회된 사용자가 없습니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("조회된 게시글이 없습니다."));

        Comment comment = Comment.create(user, post, req.getContent());

        commentRepository.save(comment);

        return comment.getId();
    }

    public Long delete(Long commentId) {

        if (commentRepository.existsById(commentId)) {

            commentRepository.deleteById(commentId);

            return commentId;
        } else {

            throw new IllegalArgumentException("조회된 댓글이 없습니다");

        }

    }
}
