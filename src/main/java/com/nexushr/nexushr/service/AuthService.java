package com.nexushr.nexushr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nexushr.nexushr.dto.LoginRequest;
import com.nexushr.nexushr.dto.RegisterRequest;
import com.nexushr.nexushr.entity.User;
import com.nexushr.nexushr.repository.UserRepository;
import com.nexushr.nexushr.security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // REGISTER
    public String register(RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole(request.getRole());

        repository.save(user);

        return "User Registered Successfully";
    }

    // LOGIN
    public String login(LoginRequest request) {

        User user =
                repository.findByEmail(request.getEmail())
                        .orElse(null);

        if(user == null) {

            return "User Not Found";
        }

        boolean matches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if(matches) {

            return jwtUtil.generateToken(user.getEmail());
        }

        return "Invalid Password";
    }
}