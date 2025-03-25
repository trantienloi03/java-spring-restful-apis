package com.trantienloi.jobhunter.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.trantienloi.jobhunter.demo.domain.User;
import com.trantienloi.jobhunter.demo.service.UserService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public User createNewUser(@RequestBody User user) {
        User loi = this.userService.handleCreateUser(user);
        return loi;
    }

    @GetMapping("/user")
    public List<User> getAllUsers() {
        List<User> lstUsers = this.userService.getAllUsers();
        return lstUsers;
    }

    @GetMapping("/user/{id}")
    public User getUserByID(@PathVariable Long id) {
        User loi = this.userService.getUserByID(id);
        return loi;
    }

    @DeleteMapping("/user/{id}")
    public String deleteUserByID(@PathVariable("id") Long id) {
        this.userService.deleteUserByID(id);
        return "Xóa thành công user với id " + id;

    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User oldUser) {
        return this.userService.handleUpdateUser(oldUser);

    }

}
