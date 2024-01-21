package com.project.questapp.controller;

import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.entity.User;

import com.project.questapp.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

// controller kullanıcıdan gelen isteklere karşılık verilen yer
@RestController
@RequestMapping("/users")

public class UserController {
/* 
    // repository tanımlamalıyız
    private userService userService;
*/
    // service repositoye bağlandığı için service kullanalım
    private UserService userService;
    // constructor enjection;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        return userService.saveOneUser(newUser);
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId) {
        // custom exveption
        return userService.getOneUserId(userId);    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {
        /* artık buna gerek yok bu kodları service katmanına alalım
        
        Optional<User> user = userService.findById(userId);
        // optional da is.present kullanımı
        // obje var mı yok mu
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setUsername(newUser.getUsername());
            foundUser.setPassword(newUser.getPassword());
            userService.save(foundUser);
            return foundUser;
        } else
            return null;

         */
        return userService.updateOneUser(userId,newUser);

    }
    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userService.deleteOneUser(userId);
    }

}
