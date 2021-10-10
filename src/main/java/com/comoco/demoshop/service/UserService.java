package com.comoco.demoshop.service;

import com.comoco.demoshop.dto.data.CreateUserData;
import com.comoco.demoshop.enums.MemberRole;
import com.comoco.demoshop.model.User;
import com.comoco.demoshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Transactional
    public void updateProfile(Long userId, String name, String email) {
        User user = this.userRepository.findByIdForUpdate(userId).get();
        user.setName(name);
        user.setEmail(email);
        this.userRepository.save(user);
    }

    @Transactional
    public void updatePassword(Long userId, String password) {
        User user = this.userRepository.findByIdForUpdate(userId).get();
        user.setPassword(password);
        this.userRepository.save(user);
    }

    @Transactional
    public void createUser(CreateUserData data) {
        User user = new User();
        user.setUsername(data.getUsername());
        user.setPassword(data.getPassword());
        user.setName(data.getName());
        user.setEmail(data.getEmail());
        user.setRole(MemberRole.Member.toString());
        this.userRepository.save(user);
    }
}
