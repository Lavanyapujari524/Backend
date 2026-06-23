package com.nexushr.nexushr.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.nexushr.nexushr.entity.User;
import com.nexushr.nexushr.repository.UserRepository;
import com.nexushr.nexushr.security.JwtUtil;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public Map<String, Object> login(
            @RequestBody User loginUser) {

        Map<String, Object> response =
                new HashMap<>();

        User user =
                userRepository
                        .findByEmail(
                                loginUser.getEmail()
                        )
                        .orElse(null);

        System.out.println(
                "Login Email: "
                        + loginUser.getEmail()
        );

        System.out.println(
                "User Found: "
                        + (user != null)
        );

        if (user != null) {

            boolean passwordMatch =
                    passwordEncoder.matches(
                            loginUser.getPassword(),
                            user.getPassword()
                    );

            System.out.println(
                    "Password Match: "
                            + passwordMatch
            );

            if (passwordMatch) {

                String token =
                        jwtUtil.generateToken(
                                user.getEmail()
                        );

                response.put(
                        "token",
                        token
                );

                response.put(
                        "role",
                        user.getRole()
                );

                response.put(
                        "message",
                        "Login Success"
                );

                return response;
            }
        }

        response.put(
                "message",
                "Invalid Credentials"
        );

        return response;
    }
}