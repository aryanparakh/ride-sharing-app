package com.Aryan.rideshare.service;

import com.Aryan.rideshare.config.JwtUtil;
import com.Aryan.rideshare.dto.LoginRequest;
import com.Aryan.rideshare.dto.RegisterRequest;
import com.Aryan.rideshare.model.User;
import com.Aryan.rideshare.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public User register(RegisterRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setRole(request.getRole());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    public String login(LoginRequest request){
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("Invalid username and password"));
        if(!passwordEncoder.matches(user.getPassword(), request.getPassword())){
            throw new RuntimeException("Invalid username and password");
        }

        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }
}
