package com.example.backend.controller;

import com.example.backend.DTOs.LoginRequestDTO;
import com.example.backend.DTOs.RegisterRequestDTO;
import com.example.backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        String token =  authService.LoginUser(loginRequestDTO);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody  RegisterRequestDTO registerRequestDTO){
        String token = authService.registerUser(registerRequestDTO);
        return ResponseEntity.ok(token);
    }
}