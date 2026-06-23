package com.nexushr.nexushr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.nexushr.nexushr.entity.Employee;
import com.nexushr.nexushr.exception.ResourceNotFoundException;
import com.nexushr.nexushr.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private AuditService auditService;

    @Autowired
    private EmailService emailService;

    // ADD EMPLOYEE
    @CacheEvict(value = "employees", allEntries = true)
    public Employee addEmployee(Employee employee) {

        Employee savedEmployee =
                repository.save(employee);

        // AUDIT LOG
        auditService.saveLog(
                "ADMIN",
                "Added Employee ID: "
                        + savedEmployee.getId()
        );

        // EMAIL NOTIFICATION
        emailService.sendEmail(
                employee.getEmail(),
                "Welcome to NexusHR",
                "Hello " + employee.getName()
                + ",\n\nWelcome to NexusHR."
                + "\nYour employee account has been created successfully."
        );

        return savedEmployee;
    }

    // SEARCH EMPLOYEE
    public List<Employee> searchEmployees(
            String keyword) {

        return repository
                .findByNameContainingIgnoreCase(
                        keyword
                );
    }

    // GET ALL EMPLOYEES
    @Cacheable("employees")
    public List<Employee> getAllEmployees() {

        System.out.println(
                "Fetching From Database..."
        );

        return repository.findAll();
    }

    // GET EMPLOYEE BY ID
    public Employee getEmployeeById(
            Long id) {

        return repository.findById(id)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee Not Found With ID: "
                                        + id
                        )
                );
    }

    // UPDATE EMPLOYEE
    @CacheEvict(value = "employees", allEntries = true)
    public Employee updateEmployee(
            Long id,
            Employee employee) {

        Employee existingEmployee =
                repository.findById(id)

                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Employee Not Found With ID: "
                                                + id
                                )
                        );

        existingEmployee.setName(
                employee.getName()
        );

        existingEmployee.setEmail(
                employee.getEmail()
        );

        existingEmployee.setDepartment(
                employee.getDepartment()
        );

        existingEmployee.setSalary(
                employee.getSalary()
        );

        Employee updatedEmployee =
                repository.save(
                        existingEmployee
                );

        // AUDIT LOG
        auditService.saveLog(
                "ADMIN",
                "Updated Employee ID: "
                        + id
        );

        return updatedEmployee;
    }

    // DELETE EMPLOYEE
    @CacheEvict(value = "employees", allEntries = true)
    public String deleteEmployee(
            Long id) {

        Employee employee =
                repository.findById(id)

                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Employee Not Found With ID: "
                                                + id
                                )
                        );

        // AUDIT LOG
        auditService.saveLog(
                "ADMIN",
                "Deleted Employee ID: "
                        + id
        );

        repository.delete(employee);

        return "Employee Deleted Successfully";
    }
}