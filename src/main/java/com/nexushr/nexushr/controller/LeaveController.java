package com.nexushr.nexushr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nexushr.nexushr.entity.LeaveRequest;
import com.nexushr.nexushr.service.LeaveService;

@RestController
@RequestMapping("/leave")
@CrossOrigin("*")
public class LeaveController {

    @Autowired
    private LeaveService service;
   
    // APPLY LEAVE
    @PostMapping("/apply")
    public LeaveRequest applyLeave(
            @RequestBody LeaveRequest leave) {

        return service.applyLeave(leave);
    }

    // GET ALL LEAVES
    @GetMapping
    public List<LeaveRequest> getAllLeaves() {

        return service.getAllLeaves();
    }

    // GET EMPLOYEE LEAVES
    @GetMapping("/employee/{employeeId}")
    public List<LeaveRequest> getEmployeeLeaves(
            @PathVariable Long employeeId) {

        return service.getEmployeeLeaves(employeeId);
    }

    // APPROVE LEAVE
    @PutMapping("/approve/{id}")
    public LeaveRequest approveLeave(
            @PathVariable Long id) {

        return service.approveLeave(id);
    }

    // REJECT LEAVE
    @PutMapping("/reject/{id}")
    public LeaveRequest rejectLeave(
            @PathVariable Long id) {

        return service.rejectLeave(id);
    }
}