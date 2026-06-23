package com.nexushr.nexushr.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexushr.nexushr.entity.Payroll;
import com.nexushr.nexushr.repository.PayrollRepository;

@Service
public class PayrollService {

    @Autowired
    private PayrollRepository repository;

    // GENERATE PAYROLL
    public Payroll generatePayroll(Payroll payroll) {

        Double netSalary =
                payroll.getBasicSalary()
                + payroll.getBonus()
                - payroll.getTax();

        payroll.setNetSalary(netSalary);

        payroll.setPaymentDate(LocalDate.now());

        return repository.save(payroll);
    }

    // GET ALL PAYROLL
    public List<Payroll> getAllPayroll() {

        return repository.findAll();
    }

    // GET EMPLOYEE PAYROLL
    public List<Payroll> getEmployeePayroll(
            Long employeeId) {

        return repository.findByEmployeeId(employeeId);
    }
}