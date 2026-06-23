package com.nexushr.nexushr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexushr.nexushr.entity.Job;

public interface JobRepository
        extends JpaRepository<Job, Long> {

}