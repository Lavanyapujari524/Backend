package com.nexushr.nexushr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nexushr.nexushr.entity.Candidate;

public interface CandidateRepository
        extends JpaRepository<Candidate, Long> {
}