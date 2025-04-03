package com.example.pironeer.service;

import com.example.pironeer.domain.User;
import com.example.pironeer.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long createUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
