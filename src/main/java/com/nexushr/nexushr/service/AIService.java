package com.nexushr.nexushr.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

@Service
public class AIService {

    @Autowired
    private RestTemplate restTemplate;

    // CALL PYTHON API
    public String predictAttrition(
            int experience,
            double salary) {

        String url =
                "http://localhost:5000/predict";

        Map<String, Object> request =
                new HashMap<>();

        request.put("experience", experience);

        request.put("salary", salary);

        HttpHeaders headers =
                new HttpHeaders();

        headers.setContentType(
                MediaType.APPLICATION_JSON
        );

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(
                        request,
                        headers
                );

        ResponseEntity<String> response =
                restTemplate.postForEntity(
                        url,
                        entity,
                        String.class
                );

        return response.getBody();
    }
}