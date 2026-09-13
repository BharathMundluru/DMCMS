package com.example.demo.service;

import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(
            String username,
            String email,
            String password) {

        System.out.println("1. RegisterUser called");
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);

        if (userRepository.findByUsername(username).isPresent()) {
            System.out.println("2. Username already exists");
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            System.out.println("3. Email already exists");
            throw new RuntimeException("Email already exists");
        }

        System.out.println("4. Creating user");

        User user = new User();

        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.RESEARCHER);

        System.out.println("5. Saving user");

        User savedUser = userRepository.save(user);

        System.out.println("6. User saved, ID = " + savedUser.getId());

        return savedUser;
    }
}