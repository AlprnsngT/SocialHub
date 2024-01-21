package com.project.questapp.service;

import java.util.List;
import java.util.Optional;

import com.project.questapp.entity.Like;
import com.project.questapp.entity.Post;
import com.project.questapp.entity.User;
import com.project.questapp.repository.LikeRepository;
import com.project.questapp.request.LikeCreateRequest;

public class LikeService {

    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;

    public LikeService(LikeRepository likeRepository, PostService postService, UserService userService) {
        this.userService = userService;
        this.postService = postService;
        this.likeRepository = likeRepository;
    }

    public List<Like> getAllLikes(Optional<Long> postId, Optional<Long> userId) {
        if (userId.isPresent() && postId.isPresent()) {
            return likeRepository.finByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return likeRepository.findByPostId(postId.get());
        }
        return likeRepository.findAll();
    }

    public Like createOneLike(LikeCreateRequest newCreateLike) {
        User user = userService.getOneUserId(newCreateLike.getUserId());
        Post post = postService.getOnePostById(newCreateLike.getPostId());
        if (user != null && post != null) {
            Like toSave = new Like();
            toSave.setId(newCreateLike.getId());
            toSave.setPost(post);
            toSave.setUser(user);
            return likeRepository.save(toSave);

        } else
            return null;
    }

    public void deleteOneLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }

    public Like getOneLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

}
