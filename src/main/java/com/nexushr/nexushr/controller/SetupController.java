package com.nexushr.nexushr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.nexushr.nexushr.entity.User;
import com.nexushr.nexushr.repository.UserRepository;

@RestController
@RequestMapping("/setup")
public class SetupController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/create-users")
    public String createUsers() {

        User admin = new User();
        admin.setName("Admin User");
        admin.setEmail("admin@nexushr.com");
        admin.setPassword(
                passwordEncoder.encode("admin123"));
        admin.setRole("ADMIN");

        User hr = new User();
        hr.setName("HR User");
        hr.setEmail("hr@nexushr.com");
        hr.setPassword(
                passwordEncoder.encode("hr123"));
        hr.setRole("HR");

        User manager = new User();
        manager.setName("Manager User");
        manager.setEmail("manager@nexushr.com");
        manager.setPassword(
                passwordEncoder.encode("manager123"));
        manager.setRole("MANAGER");

        User employee = new User();
        employee.setName("Employee User");
        employee.setEmail("employee@nexushr.com");
        employee.setPassword(
                passwordEncoder.encode("employee123"));
        employee.setRole("EMPLOYEE");

        userRepository.save(admin);
        userRepository.save(hr);
        userRepository.save(manager);
        userRepository.save(employee);

        return "Users Created Successfully";
    }
}