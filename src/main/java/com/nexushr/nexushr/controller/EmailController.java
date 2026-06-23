package com.nexushr.nexushr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nexushr.nexushr.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/test")
    public String sendTestEmail() {

        emailService.sendEmail(
                "lavanyapujari261@gmail.com",
                "NexusHR Test",
                "Email module working successfully"
        );

        return "Email Sent Successfully";
    }
}