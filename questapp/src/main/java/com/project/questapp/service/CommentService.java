package com.project.questapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.questapp.entity.Comment;
import com.project.questapp.entity.Post;
import com.project.questapp.entity.User;
import com.project.questapp.repository.CommentRepository;
import com.project.questapp.request.CommentCreateRequest;
import com.project.questapp.request.CommentUpdateRequest;

@Service
public class CommentService {
    // 1.adım
    // servicenin reposunu ekle
    // 2.adım
    // kullanıcı olduğu için user service ekle
    // 3.adım
    // constructor ekle

    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
        this.commentRepository = commentRepository;
    }

    // burası önemli
    // tüm commentler ile usera özel commentleri getirmeyi
    // tek fonksiyonda yapabilmek için optional kullanırız
    public List<Comment> getAllComment(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        }
        return commentRepository.findAll();
    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest newCreateComment) {
        // user controlü yap
        User user = userService.getOneUserId(newCreateComment.getUserId());
        Post post = postService.getOnePostById(newCreateComment.getPostId());
        if (user != null && post != null) {
            Comment toSave = new Comment();
            toSave.setId(newCreateComment.getId());
            toSave.setText(newCreateComment.getText());
            toSave.setUser(user);
            toSave.setPost(post);
            return commentRepository.save(toSave);
        }else
            return null;

    }

    public Comment updateOneCommentById(Long commenId, CommentUpdateRequest newCommentUpdateRequest) {
        Optional<Comment> comment = commentRepository.findById(commenId);
        if (comment.isPresent()) {
            Comment toUpdateComment = new Comment();
            toUpdateComment.setText(newCommentUpdateRequest.getText());
            commentRepository.save(toUpdateComment);
            return toUpdateComment;
        }else
            return null;
    }

    public void deleteOneCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}
