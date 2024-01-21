package com.project.questapp.controller;

import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.entity.Comment;
import com.project.questapp.request.CommentCreateRequest;
import com.project.questapp.request.CommentUpdateRequest;
import com.project.questapp.service.CommentService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/comments")

public class CommentController {
    // 1.Adım
    // service ekle
    private CommentService commentService;
    
    // 2.adım
    // constructor tanımla
    public CommentController (CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping // kullanıcının commentleri dönecek -- // userId veya postId ile çağırabilmeliyiz
    public List<Comment> getAllComment(@RequestParam Optional <Long> userId,@RequestParam Optional<Long> postId){
        // service den dönecek
        return commentService.getAllComment(userId,postId);
    }

    @GetMapping("{commentId}")
    public Comment getOneComment(@PathVariable Long commentId) {
        return commentService.getOneCommentById(commentId);
    }

    @PostMapping("{commentId}")
    public Comment createOneComment(@RequestBody CommentCreateRequest newCreateComment) {
        return commentService.createOneComment(newCreateComment);
    }

    @PutMapping("{commentId}")
    public Comment updateComment(@PathVariable Long commenId, @RequestBody CommentUpdateRequest newCommentUpdateRequest) {
        return commentService.updateOneCommentById(commenId,newCommentUpdateRequest);
    }
    @DeleteMapping("{commentId}")
    public void deleteComment(@PathVariable Long commentId){
        commentService.deleteOneCommentById(commentId);
    }
    
    
    
}
