package com.project.questapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.questapp.entity.Post;
import com.project.questapp.entity.User;
import com.project.questapp.repository.PostRepository;
import com.project.questapp.request.PostCreateRequest;
import com.project.questapp.request.PostUpdateRequest;

import jakarta.persistence.PostUpdate;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserService userService;

    public PostService(PostRepository postRepository,UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<Post> getAllPosts(Optional<Long> userId) {
        // controller da optional çağırıyoruz
        // bu yüzden eğer user var ise ona ait postları
        // yoksa 26.satır
        if (userId.isPresent()) {
            return postRepository.findByUserId(userId.get());
        }
        return postRepository.findAll();
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        /*
         * user var mı yok mu işlemini userService de yaptığmız için userService
         * bağlanmalıyız
         * postta user objesine biz nullable kısmına false demiştik
         * yani user bilgisi gelmek zorunda demiştik
         * bu yüzden post create işlemi yaparken user validation yapılmalı
         * postmanda user objesi vermek uygun değildir
         * şimdi bir de user objesinin tüm bilgilerine ihtiyaç yok
         * lazı olan şey userId
         * bu yüzden istekler için yeni bir katman oluşturuyoruz.
         */

        // user validation işlemi user service de var
        User user = userService.getOneUserId(newPostRequest.getUserId());
        if (user == null)
            return null;
        Post toSave = new Post();
        toSave.setId(newPostRequest.getId());
        toSave.setText(newPostRequest.getText());
        toSave.setTitle(newPostRequest.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    public Post updateOnePostById(Long postId,PostUpdateRequest newpostUpdate) {
        // postu al id ile
        Optional<Post> post = postRepository.findById((postId));
        if(post.isPresent()){
            Post toUpdatePost = post.get();
            toUpdatePost.setText(newpostUpdate.getText());
            toUpdatePost.setTitle(newpostUpdate.getTitle());
            postRepository.save(toUpdatePost);
            return toUpdatePost;

        }
        return null;
    }

    public void deleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }
}
