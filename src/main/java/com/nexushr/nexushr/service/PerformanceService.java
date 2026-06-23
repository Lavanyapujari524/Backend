package com.nexushr.nexushr.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexushr.nexushr.entity.PerformanceReview;
import com.nexushr.nexushr.repository.PerformanceRepository;

@Service
public class PerformanceService {

    @Autowired
    private PerformanceRepository repository;

    // ADD REVIEW
    public PerformanceReview addReview(
            PerformanceReview review) {

        review.setReviewDate(LocalDate.now());

        return repository.save(review);
    }

    // GET ALL REVIEWS
    public List<PerformanceReview> getAllReviews() {

        return repository.findAll();
    }

    // GET EMPLOYEE REVIEWS
    public List<PerformanceReview> getEmployeeReviews(
            Long employeeId) {

        return repository.findByEmployeeId(employeeId);
    }

    // UPDATE REVIEW
    public PerformanceReview updateReview(
            Long id,
            PerformanceReview updatedReview) {

        PerformanceReview review =
                repository.findById(id).orElse(null);

        if(review != null) {

            review.setGoals(updatedReview.getGoals());
            review.setFeedback(updatedReview.getFeedback());
            review.setRating(updatedReview.getRating());

            return repository.save(review);
        }

        return null;
    }
}