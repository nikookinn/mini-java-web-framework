package dev.nikookinn.controller;

import dev.nikookinn.annotations.GetMapping;
import dev.nikookinn.annotations.MyController;
import dev.nikookinn.annotations.PostMapping;
import dev.nikookinn.model.User;
/**
 * UserController - Controller for user-related endpoints
 * This controller provides endpoints to create and retrieve user data.
 */
@MyController
public class UserController {

    @PostMapping("/user")
    public User createUser(User user) {
        return user;
    }
    @GetMapping("/api/user")
    public User getUser(){
        User user = new User();
        user.setUsername("skywalker");
        user.setPassword("1234");
        user.setAge(20);
        return user;
    }
}
