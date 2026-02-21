package com.Aryan.rideshare.controller;

import com.Aryan.rideshare.dto.LoginRequest;
import com.Aryan.rideshare.dto.RegisterRequest;
import com.Aryan.rideshare.model.User;
import com.Aryan.rideshare.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request){
        return authService.register(request);
    }

    @PostMapping("login")
    public String login(@RequestBody LoginRequest request){
        return authService.login(request);
    }


}
