package com.nexushr.nexushr.service;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexushr.nexushr.entity.Employee;
import com.nexushr.nexushr.repository.EmployeeRepository;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportService {

    @Autowired
    private EmployeeRepository repository;

    // EXPORT EMPLOYEE CSV
    public void exportEmployeeCSV(
            HttpServletResponse response) {

        try {

            response.setContentType("text/csv");

            response.setHeader(
                    "Content-Disposition",
                    "attachment; filename=employees.csv"
            );

            List<Employee> employees =
                    repository.findAll();

            PrintWriter writer =
                    response.getWriter();

            // CSV HEADER
            writer.println(
                    "ID,Name,Email,Department"
            );

            // CSV DATA
            for(Employee employee : employees) {

                writer.println(
                        employee.getId() + ","
                        + employee.getName() + ","
                        + employee.getEmail() + ","
                        + employee.getDepartment()
                );
            }

            writer.flush();

            writer.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}