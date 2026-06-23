package com.nexushr.nexushr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nexushr.nexushr.entity.PerformanceReview;
import com.nexushr.nexushr.service.PerformanceService;

@RestController
@RequestMapping("/performance")
@CrossOrigin("*")
public class PerformanceController {

    @Autowired
    private PerformanceService service;

    // ADD REVIEW
    @PostMapping("/add")
    public PerformanceReview addReview(
            @RequestBody PerformanceReview review) {

        return service.addReview(review);
    }

    // GET ALL REVIEWS
    @GetMapping
    public List<PerformanceReview> getAllReviews() {

        return service.getAllReviews();
    }

    // GET EMPLOYEE REVIEWS
    @GetMapping("/employee/{employeeId}")
    public List<PerformanceReview> getEmployeeReviews(
            @PathVariable Long employeeId) {

        return service.getEmployeeReviews(employeeId);
    }

    // UPDATE REVIEW
    @PutMapping("/update/{id}")
    public PerformanceReview updateReview(
            @PathVariable Long id,
            @RequestBody PerformanceReview review) {

        return service.updateReview(id, review);
    }
}