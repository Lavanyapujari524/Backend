package com.nexushr.nexushr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nexushr.nexushr.entity.Payroll;
import com.nexushr.nexushr.service.PayrollService;

@RestController
@RequestMapping("/payroll")
@CrossOrigin("*")
public class PayrollController {

    @Autowired
    private PayrollService service;

    // GENERATE PAYROLL
    @PostMapping("/generate")
    public Payroll generatePayroll(
            @RequestBody Payroll payroll) {

        return service.generatePayroll(payroll);
    }

    // GET ALL PAYROLL
    @GetMapping
    public List<Payroll> getAllPayroll() {

        return service.getAllPayroll();
    }

    // GET EMPLOYEE PAYROLL
    @GetMapping("/employee/{employeeId}")
    public List<Payroll> getEmployeePayroll(
            @PathVariable Long employeeId) {

        return service.getEmployeePayroll(employeeId);
    }
}