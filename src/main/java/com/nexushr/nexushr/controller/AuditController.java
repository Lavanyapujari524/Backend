package com.nexushr.nexushr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nexushr.nexushr.entity.AuditLog;
import com.nexushr.nexushr.service.AuditService;

@RestController
@RequestMapping("/audit")
@CrossOrigin("*")
public class AuditController {

    @Autowired
    private AuditService service;

    // GET ALL LOGS
    @GetMapping
    public List<AuditLog> getAllLogs() {

        return service.getAllLogs();
    }
}