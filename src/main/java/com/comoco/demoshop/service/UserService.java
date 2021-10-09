package com.comoco.demoshop.service;

import com.comoco.demoshop.model.User;
import com.comoco.demoshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    public void UpdateProfile(Long userId, String name, String email) {
        User user = this.userRepository.findById(userId).get();
        user.setName(name);
        user.setEmail(email);
        this.userRepository.save(user);
    }
}
