package com.example.backend.service;

import com.example.backend.DTOs.LoginRequestDTO;
import com.example.backend.DTOs.RegisterRequestDTO;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public String registerUser(RegisterRequestDTO registerRequestDTO) {

        Optional<User> userEmail = userRepository.findByEmail(registerRequestDTO.getEmail());
        if (userEmail.isPresent()) {
            throw new RuntimeException("Ce email a deja ete utilise !");
        }

        User userEntity = new User();
        userEntity.setName(registerRequestDTO.getName());
        userEntity.setEmail(registerRequestDTO.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        userEntity.setCreatedAt(LocalDate.now());
        userRepository.save(userEntity);

        String token = jwtService.generateToken(userEntity);
        return token;
    }

    public String LoginUser(LoginRequestDTO loginRequestDTO) {

        Optional<User> userEmail = Optional.of(userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur Introuvable")));


        Boolean passwordMatch = passwordEncoder.matches(loginRequestDTO.getPassword(), userEmail.get().getPassword());

        if (passwordMatch) {
            String token = jwtService.generateToken(userEmail.get());
            return token;
        }else{
            throw new RuntimeException("Mot de passe incorrecte");
        }
    }
}
