package com.project.questapp.request;

import lombok.Data;

// bu katmanın adı DTO da olabilir.

@Data
public class PostCreateRequest {
    
    /*
     * Create etmek için gerekli olan şeyler
     * id
     * title
     * text
     */
    Long id;
    String text;
    String title;
    Long userId;
}
