package com.trantienloi.jobhunter.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.trantienloi.jobhunter.demo.domain.RestResponse;
import com.trantienloi.jobhunter.demo.domain.User;
import com.trantienloi.jobhunter.demo.service.UserService;
import com.trantienloi.jobhunter.demo.service.errors.IdInvalidException;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    // local
    // @ExceptionHandler(value = IdInvalidException.class)
    // public ResponseEntity<String> handleIdException(IdInvalidException
    // IdException) {
    // RestResponse<Object> rs = new RestResponse<>();
    // return ResponseEntity.badRequest().body(IdException.getMessage());
    // }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<RestResponse<String>> deleteUserByID(@PathVariable("id") Long id) throws IdInvalidException {
        if (id > 1500) {
            throw new IdInvalidException("id khong lon hon 1500");
        }
        this.userService.deleteUserByID(id);
        RestResponse<String> rs = new RestResponse<>();
        rs.setData("Xóa thành công user với id " + id);
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("CALL API SUCCESS");
        return ResponseEntity.status(HttpStatus.OK).body(rs);

    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User oldUser) {
        User user = this.userService.handleUpdateUser(oldUser);
        return ResponseEntity.ok(user);

    }

}
