package com.project.questapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.questapp.entity.User;
import com.project.questapp.repository.UserRepository;

/*
 * service katmanı ne işe yarar
 * databaseye bağlanan bir repository katmanı vardır
 * service katmanı repositoryden bilgi alır
 * bu bilgiler üzerinde işlemler/değişiklikler/kontroller
 * service bu bilgileri controller katmanını gönderecek
 * controllere sadece service katmanından bilgi gelecek
 */

@Service
public class UserService {
    private UserRepository userRepository;
    //repositorye bağlantı
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User saveOneUser(User newUser) {
        return userRepository.save(newUser);
    }
    public User getOneUserId(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
    public User updateOneUser(Long userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);
        // optional da is.present kullanımı
        // obje var mı yok mu
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setUsername(newUser.getUsername());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        } else
            return null;

    }
    public void deleteOneUser(Long userId) {
        userRepository.deleteById(userId);
    }

}
