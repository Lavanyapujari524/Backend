package com.nexushr.nexushr.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notify")

public class NotificationController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public String sendNotification(
            @RequestParam String message) {

        messagingTemplate.convertAndSend(
                "/topic/notifications",
                message
        );

        return "Notification Sent";
    }
}