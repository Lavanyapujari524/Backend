package com.nexushr.nexushr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nexushr.nexushr.entity.Candidate;
import com.nexushr.nexushr.service.CandidateService;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/candidates")
@CrossOrigin("*")
public class CandidateController {

    @Autowired
    private CandidateService service;

    @PostMapping
    public Candidate addCandidate(
            @RequestBody Candidate candidate) {

        return service.addCandidate(candidate);
    }
    @PostMapping("/upload")
    public String uploadResume(
            @RequestParam("file")
            MultipartFile file)
            throws Exception {

        String uploadDir =
                "uploads/resumes/";

        Path path =
                Paths.get(
                        uploadDir
                        + file.getOriginalFilename()
                );

        Files.write(
                path,
                file.getBytes()
        );

        return file.getOriginalFilename();
    }
    @GetMapping("/resume/{fileName}")
    public ResponseEntity<Resource>
    downloadResume(
            @PathVariable String fileName)
            throws Exception {

        Path path =
                Paths.get(
                        "uploads/resumes/"
                        + fileName
                );

        Resource resource =
                new UrlResource(
                        path.toUri()
                );

        return ResponseEntity.ok()
                .body(resource);
    }
    @PutMapping("/{id}/status")
    public Candidate updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return service.updateStatus(
                id,
                status
        );
    }
    @GetMapping
    public List<Candidate> getAllCandidates() {

        return service.getAllCandidates();
    }
}