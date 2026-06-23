package com.nexushr.nexushr.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexushr.nexushr.entity.AuditLog;
import com.nexushr.nexushr.repository.AuditRepository;

@Service
public class AuditService {

    @Autowired
    private AuditRepository repository;

    // SAVE LOG
    public void saveLog(
            String username,
            String action) {

        AuditLog log =
                new AuditLog();

        log.setUsername(username);

        log.setAction(action);

        log.setTimestamp(
                LocalDateTime.now()
        );

        repository.save(log);
    }

    // GET ALL LOGS
    public List<AuditLog> getAllLogs() {

        return repository.findAll();
    }
}