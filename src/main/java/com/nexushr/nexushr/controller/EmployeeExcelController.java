package com.nexushr.nexushr.controller;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.nexushr.nexushr.entity.Employee;
import com.nexushr.nexushr.repository.EmployeeRepository;

@RestController
@RequestMapping("/excel")
@CrossOrigin("*")
public class EmployeeExcelController {

    @Autowired
    private EmployeeRepository repository;

    @GetMapping("/employees")
    public ResponseEntity<byte[]> exportEmployees()
            throws Exception {

        Workbook workbook =
                new XSSFWorkbook();

        Sheet sheet =
                workbook.createSheet(
                        "Employees"
                );

        Row header =
                sheet.createRow(0);

        header.createCell(0)
                .setCellValue("ID");

        header.createCell(1)
                .setCellValue("Name");

        header.createCell(2)
                .setCellValue("Email");

        header.createCell(3)
                .setCellValue("Department");

        header.createCell(4)
                .setCellValue("Salary");

        List<Employee> employees =
                repository.findAll();

        int rowNum = 1;

        for (Employee emp : employees) {

            Row row =
                    sheet.createRow(
                            rowNum++
                    );

            row.createCell(0)
                    .setCellValue(
                            emp.getId()
                    );

            row.createCell(1)
                    .setCellValue(
                            emp.getName()
                    );

            row.createCell(2)
                    .setCellValue(
                            emp.getEmail()
                    );

            row.createCell(3)
                    .setCellValue(
                            emp.getDepartment()
                    );

            row.createCell(4)
                    .setCellValue(
                            emp.getSalary()
                    );
        }

        ByteArrayOutputStream out =
                new ByteArrayOutputStream();

        workbook.write(out);

        workbook.close();

        return ResponseEntity.ok()

                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=employees.xlsx"
                )

                .contentType(
                        MediaType.APPLICATION_OCTET_STREAM
                )

                .body(
                        out.toByteArray()
                );
    }
}