package com.project.questapp.controller;

import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.entity.Like;
import com.project.questapp.request.LikeCreateRequest;
import com.project.questapp.service.LikeService;

import java.util.List;
import java.util.Optional;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RequestMapping("/likes")
@RestController
public class LikeController {

    private LikeService likeService;

    public LikeController(LikeService likeService){
        this.likeService = likeService;
    }
    @GetMapping()
    public List<Like> getAllLike(@RequestParam Optional <Long> postId,@RequestParam Optional <Long> userId) {
        return likeService.getAllLikes(postId,userId);
    }
    @GetMapping("{likeId}")
    public Like getOneLike(@PathVariable Long likeId) {
        return likeService.getOneLikeById(likeId);
    }
    
    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateRequest newCreateLike) {
        //TODO: process POST request
        
        return likeService.createOneLike(newCreateLike);
    }
    @DeleteMapping("{likeId}")
    public void deleteLike(@PathVariable Long likeId){
        likeService.deleteOneLikeById(likeId);
    }
    
    
}
