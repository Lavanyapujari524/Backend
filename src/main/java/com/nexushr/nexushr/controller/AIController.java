package com.nexushr.nexushr.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.nexushr.nexushr.service.AIService;

@RestController
@RequestMapping("/ai")
@CrossOrigin("*")
public class AIController {

    @Autowired
    private AIService service;

    @GetMapping("/predict")
    public String predictAttrition(

            @RequestParam int experience,

            @RequestParam double salary) {

        return service.predictAttrition(
                experience,
                salary
        );
    }
}