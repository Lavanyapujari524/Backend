package com.nexushr.nexushr.controller;
import com.nexushr.nexushr.service.PdfService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nexushr.nexushr.entity.Attendance;
import com.nexushr.nexushr.entity.Employee;
import com.nexushr.nexushr.entity.LeaveRequest;
import com.nexushr.nexushr.entity.Payroll;

import com.nexushr.nexushr.repository.AttendanceRepository;
import com.nexushr.nexushr.repository.EmployeeRepository;
import com.nexushr.nexushr.repository.LeaveRepository;
import com.nexushr.nexushr.repository.PayrollRepository;
import com.nexushr.nexushr.service.ReportService;
@RestController
@RequestMapping("/reports")
@CrossOrigin("*")
public class ReportController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private PayrollRepository payrollRepository;
    
    @Autowired
    private ReportService reportService;
    @Autowired
    private PdfService pdfService;
    // EMPLOYEE REPORT
    @GetMapping("/employees")
    public List<Employee> employeeReport() {

        return employeeRepository.findAll();
    }
    @GetMapping("/employees/csv")
    public void exportEmployeesCSV(
            jakarta.servlet.http.HttpServletResponse response) {

        reportService.exportEmployeeCSV(response);
    }
    @GetMapping("/employees/pdf")
    public void exportEmployeePdf(
           jakarta.servlet.http.HttpServletResponse response) {

       pdfService.exportEmployeePdf(response);
   }
    // ATTENDANCE REPORT
    @GetMapping("/attendance")
    public List<Attendance> attendanceReport() {

        return attendanceRepository.findAll();
    }

    // LEAVE REPORT
    @GetMapping("/leaves")
    public List<LeaveRequest> leaveReport() {

        return leaveRepository.findAll();
    }

    // PAYROLL REPORT
    @GetMapping("/payroll")
    public List<Payroll> payrollReport() {

        return payrollRepository.findAll();
    }
}