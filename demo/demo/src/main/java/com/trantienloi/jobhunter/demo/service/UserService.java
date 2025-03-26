package com.trantienloi.jobhunter.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.trantienloi.jobhunter.demo.domain.User;
import com.trantienloi.jobhunter.demo.respository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handleCreateUser(User user) {
        return this.userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User getUserByID(Long id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (userOptional.isPresent())
            return userOptional.get();
        return null;
    }

    public void deleteUserByID(Long id) {
        this.userRepository.deleteById(id);
    }

    public User handleUpdateUser(User oldUser) {
        User newUser = this.getUserByID(oldUser.getId());
        if (newUser != null) {
            newUser.setEmail(oldUser.getEmail());
            newUser.setName(oldUser.getName());
            newUser.setPassword(oldUser.getPassword());
            newUser = this.userRepository.save(newUser);
        }
        return this.userRepository.save(newUser);
    }

    public User handleGetUserByEmail(String username) {
        return this.userRepository.findByEmail(username);
    }
}
