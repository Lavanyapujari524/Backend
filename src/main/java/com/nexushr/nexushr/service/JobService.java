package com.nexushr.nexushr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexushr.nexushr.entity.Job;
import com.nexushr.nexushr.repository.JobRepository;

@Service
public class JobService {

    @Autowired
    private JobRepository repository;

    // ADD JOB
    public Job addJob(Job job) {

        return repository.save(job);
    }

    // GET ALL JOBS
    public List<Job> getAllJobs() {

        return repository.findAll();
    }
}