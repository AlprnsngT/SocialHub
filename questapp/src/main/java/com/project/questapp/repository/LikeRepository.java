package com.project.questapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.questapp.entity.Like;

public interface LikeRepository extends JpaRepository<Like,Long>{

    List<Like> findByPostId(Long long1);

    List<Like> finByUserIdAndPostId(Long long1, Long long2);

    List<Like> findByUserId(Long long1);
    
}
