package com.nexushr.nexushr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexushr.nexushr.entity.Candidate;
import com.nexushr.nexushr.repository.CandidateRepository;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository repository;

    @Autowired
    private EmailService emailService;

    // ADD CANDIDATE
    public Candidate addCandidate(
            Candidate candidate) {

        candidate.setStatus("APPLIED");

        return repository.save(candidate);
    }

    // GET ALL CANDIDATES
    public List<Candidate> getAllCandidates() {

        return repository.findAll();
    }

    // UPDATE STATUS
    public Candidate updateStatus(
            Long id,
            String status) {

        Candidate candidate =
                repository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Candidate Not Found"
                                )
                        );

        candidate.setStatus(status);

        Candidate updated =
                repository.save(candidate);

        // EMAIL NOTIFICATIONS

        if ("SELECTED".equals(status)) {

            emailService.sendEmail(
                    candidate.getEmail(),
                    "Interview Result",
                    "Congratulations! You have been selected."
            );
        }

        if ("REJECTED".equals(status)) {

            emailService.sendEmail(
                    candidate.getEmail(),
                    "Interview Result",
                    "Thank you for attending. We will contact you for future opportunities."
            );
        }

        if ("SHORTLISTED".equals(status)) {

            emailService.sendEmail(
                    candidate.getEmail(),
                    "Application Update",
                    "Congratulations! You have been shortlisted."
            );
        }

        if ("INTERVIEW_SCHEDULED".equals(status)) {

            emailService.sendEmail(
                    candidate.getEmail(),
                    "Interview Scheduled",
                    "Your interview has been scheduled. HR will contact you shortly."
            );
        }

        return updated;
    }
}