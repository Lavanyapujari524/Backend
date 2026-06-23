package com.nexushr.nexushr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.nexushr.nexushr.entity.Employee;
import com.nexushr.nexushr.repository.EmployeeRepository;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class PdfService {

    @Autowired
    private EmployeeRepository repository;

    // EXPORT EMPLOYEE PDF
    public void exportEmployeePdf(
            HttpServletResponse response) {

        try {

            response.setContentType(
                    "application/pdf"
            );

            response.setHeader(
                    "Content-Disposition",
                    "attachment; filename=employees.pdf"
            );

            Document document =
                    new Document();

            PdfWriter.getInstance(
                    document,
                    response.getOutputStream()
            );

            document.open();

            // TITLE
            document.add(
                    new Paragraph(
                            "Employee Report"
                    )
            );

            document.add(
                    new Paragraph(" ")
            );

            // TABLE
            PdfPTable table =
                    new PdfPTable(4);

            table.addCell("ID");
            table.addCell("Name");
            table.addCell("Email");
            table.addCell("Department");

            List<Employee> employees =
                    repository.findAll();

            for(Employee employee : employees) {

                table.addCell(
                        String.valueOf(
                                employee.getId()
                        )
                );

                table.addCell(
                        employee.getName()
                );

                table.addCell(
                        employee.getEmail()
                );

                table.addCell(
                        employee.getDepartment()
                );
            }

            document.add(table);

            document.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}