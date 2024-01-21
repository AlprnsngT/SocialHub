package com.project.questapp.controller;

import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.service.PostService;

import jakarta.persistence.PostUpdate;

import com.project.questapp.entity.Post;
import com.project.questapp.request.PostCreateRequest;
import com.project.questapp.request.PostUpdateRequest;

import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/posts")

public class PostController {

    private PostService postService;
    
    public PostController(PostService postService){
        this.postService = postService;
    }
    
    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional <Long> userId) {
        return postService.getAllPosts(userId);
    }

    @GetMapping("{postId}")
    public Post getOnePost(@PathVariable Long postId) {
        return postService.getOnePostById(postId);
    }
    
    //
    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostRequest){
        return postService.createOnePost(newPostRequest);
    }

    @PutMapping("{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest newPostUpdateRequest) {
        return postService.updateOnePostById(postId,newPostUpdateRequest);
    }

    @DeleteMapping("{postId}")
    public void deleteOnePost(@PathVariable Long postId){
        postService.deleteOnePostById(postId);
    }
    
    
}
