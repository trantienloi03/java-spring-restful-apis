package com.trantienloi.jobhunter.demo.service;

import org.springframework.stereotype.Service;

import com.trantienloi.jobhunter.demo.domain.User;
import com.trantienloi.jobhunter.demo.respository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void handlerCreateUser(User user) {
        this.userRepository.save(user);
    }
}
