package com.project.questapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.questapp.entity.Post;

public interface PostRepository extends JpaRepository<Post,Long>{
    /*
     * fonksiyonları JpaRepository den çekiyoruz
     * findById...... bu fonksiyonda noktalı yere 
     * obje içerisindeki aradığımız şeyi birleştirebiliyoruz
     * example
     * post içerisinde olanlar : title text
     * findByText , findByTitle ...
    */
    List<Post> findByUserId(Long userId);
    
}
