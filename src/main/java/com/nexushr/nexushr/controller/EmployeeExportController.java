package com.nexushr.nexushr.controller;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nexushr.nexushr.entity.Employee;
import com.nexushr.nexushr.repository.EmployeeRepository;

@RestController
@RequestMapping("/export")
@CrossOrigin("*")
public class EmployeeExportController {

    @Autowired
    private EmployeeRepository repository;

    @GetMapping("/employees")
    public ResponseEntity<String> exportEmployees() {

        List<Employee> employees =
                repository.findAll();

        StringBuilder csv =
                new StringBuilder();

        csv.append(
                "ID,Name,Email,Department,Salary\n"
        );

        for (Employee employee : employees) {

            csv.append(employee.getId())
               .append(",")
               .append(employee.getName())
               .append(",")
               .append(employee.getEmail())
               .append(",")
               .append(employee.getDepartment())
               .append(",")
               .append(employee.getSalary())
               .append("\n");
        }

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=employees.csv"
                )
                .contentType(
                        MediaType.TEXT_PLAIN
                )
                .body(csv.toString());
    }
}