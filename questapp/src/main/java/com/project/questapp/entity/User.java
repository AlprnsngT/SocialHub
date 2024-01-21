package com.project.questapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user")
@Data // user classının getter ve setter fonksiyonları generate edecek

public class User {

    @Id
    Long id;
    
    String username;
    String password;
    
}
