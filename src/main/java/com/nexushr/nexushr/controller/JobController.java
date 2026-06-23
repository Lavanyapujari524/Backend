package com.nexushr.nexushr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nexushr.nexushr.entity.Job;
import com.nexushr.nexushr.service.JobService;

@RestController
@RequestMapping("/jobs")
@CrossOrigin("*")
public class JobController {

    @Autowired
    private JobService service;

    // ADD JOB
    @PostMapping("/add")
    public Job addJob(
            @RequestBody Job job) {

        return service.addJob(job);
    }

    // GET ALL JOBS
    @GetMapping
    public List<Job> getAllJobs() {

        return service.getAllJobs();
    }
}