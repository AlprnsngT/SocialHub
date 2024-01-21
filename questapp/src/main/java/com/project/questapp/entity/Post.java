package com.project.questapp.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "post")
@Data
public class Post {
    
    @Id
    Long id;

    // mapping işlemleri --tabloları birbirine bağlama - ManyToOne OneToMany

    /*
     * birçok postun tek bir kullanıcısı vardır
     * fetchtype.lazy ilgili postu çağırdığım zaman user ile bağlantısı olduğu için
     * user bilgilerinin hepsini getirmesini engelliyorum
     */
    @ManyToOne(fetch = FetchType.LAZY)

    // bu post tablosu içerisindeki user_id column usera bağlıyorum
    @JoinColumn(name = "user_id", nullable = false)

    // kullanıcı silindiği zaman ona ait bütün postları da silinmeli
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    User user;
    
    String title;
    @Lob
    @Column(columnDefinition = "text")
    String text;

}
