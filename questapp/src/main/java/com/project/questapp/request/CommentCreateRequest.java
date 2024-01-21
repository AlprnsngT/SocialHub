package com.project.questapp.request;

import lombok.Data;

@Data
public class CommentCreateRequest {
    Long id;
    String text;

    // commentleri var olan post ve kullanıcıya atmamız lazım
    // userId ve postId tanımla
    Long userId;
    Long postId;
    
}
