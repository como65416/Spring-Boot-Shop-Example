package com.comoco.demoshop.service;

import com.comoco.demoshop.dto.data.CreateUserData;
import com.comoco.demoshop.enums.MemberRole;
import com.comoco.demoshop.model.User;
import com.comoco.demoshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public Optional<User> validatedUserPassword(String username, String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Optional<User> user = this.userRepository.findByUsername(username);
        if (user.isEmpty() || !bCryptPasswordEncoder.matches(password, user.get().getPassword())) {
            return Optional.empty();
        }

        return user;
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
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = bCryptPasswordEncoder.encode(password);

        User user = this.userRepository.findByIdForUpdate(userId).get();
        user.setPassword(hashedPassword);
        this.userRepository.save(user);
    }

    @Transactional
    public void createUser(CreateUserData data) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = bCryptPasswordEncoder.encode(data.getPassword());

        User user = new User();
        user.setUsername(data.getUsername());
        user.setPassword(hashedPassword);
        user.setName(data.getName());
        user.setEmail(data.getEmail());
        user.setRole(MemberRole.Member.name());
        this.userRepository.save(user);
    }
}
