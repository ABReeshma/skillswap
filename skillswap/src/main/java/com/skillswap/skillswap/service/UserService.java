package com.skillswap.skillswap.service;

import com.skillswap.skillswap.dto.LoginRequest;
import com.skillswap.skillswap.dto.RegisterRequest;
import com.skillswap.skillswap.model.User;
import com.skillswap.skillswap.repository.UserRepository;
import com.skillswap.skillswap.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setName(request.getName());   // 👈 IMPORTANT
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);

        return "User registered successfully";
    }


    public String login(LoginRequest loginRequest) {

        User dbUser = userRepository.findByEmail(loginRequest.getEmail());

        if (dbUser == null) {
            throw new RuntimeException("User not found");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(dbUser.getEmail(), dbUser.getRole());
    }
}
