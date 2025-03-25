package com.trantienloi.jobhunter.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.trantienloi.jobhunter.demo.domain.User;
import com.trantienloi.jobhunter.demo.service.UserService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/users")
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        User loi = this.userService.handleCreateUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(loi);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> lstUsers = this.userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(lstUsers);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable Long id) {
        User loi = this.userService.getUserByID(id);
        return ResponseEntity.status(HttpStatus.OK).body(loi);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserByID(@PathVariable("id") Long id) {
        this.userService.deleteUserByID(id);
        return ResponseEntity.status(HttpStatus.OK).body("Xóa thành công user với id " + id);

    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User oldUser) {
        User user = this.userService.handleUpdateUser(oldUser);
        return ResponseEntity.ok(user);

    }

}
