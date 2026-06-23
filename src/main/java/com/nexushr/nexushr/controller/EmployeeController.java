package com.nexushr.nexushr.controller;
import com.nexushr.nexushr.entity.Employee;
import com.nexushr.nexushr.service.EmployeeService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/employees")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    // ADD EMPLOYEE
    @PostMapping
    public Employee addEmployee(
            @RequestBody Employee employee) {

        return service.addEmployee(employee);
    }

    // GET ALL EMPLOYEES
    @GetMapping
    public List<Employee> getAllEmployees() {

        return service.getAllEmployees();
    }
    @PostMapping(
            value = "/upload-photo",
            consumes = {"multipart/form-data"}
    )
    public String uploadPhoto(
            @RequestParam("file")
            MultipartFile file)
            throws Exception {

        String uploadDir =
                "uploads/profile-photos/";

        java.io.File dir =
                new java.io.File(uploadDir);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName =
                System.currentTimeMillis()
                + "_"
                + file.getOriginalFilename();

        Path path =
                Paths.get(uploadDir + fileName);

        Files.write(
                path,
                file.getBytes()
        );

        return fileName;
    }
    // GET EMPLOYEE BY ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(
            @PathVariable Long id) {

        return service.getEmployeeById(id);
    }
    @GetMapping("/search")
    public List<Employee> searchEmployees(
            @RequestParam String keyword) {

        return service.searchEmployees(
                keyword
        );
    }
    // UPDATE EMPLOYEE
    @PutMapping("/{id}")
    public Employee updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee) {

        return service.updateEmployee(
                id,
                employee
        );
    }

    // DELETE EMPLOYEE
    @DeleteMapping("/{id}")
    public String deleteEmployee(
            @PathVariable Long id) {

        return service.deleteEmployee(id);
    }
}