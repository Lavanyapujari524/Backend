package com.nexushr.nexushr.controller;

import java.io.ByteArrayOutputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
@RequestMapping("/payslip")
@CrossOrigin("*")
public class PayslipController {

    @GetMapping("/{employeeId}")
    public ResponseEntity<byte[]> generatePayslip(
            @PathVariable Long employeeId)
            throws Exception {

        ByteArrayOutputStream out =
                new ByteArrayOutputStream();

        Document document =
                new Document();

        PdfWriter.getInstance(
                document,
                out
        );

        document.open();

        document.add(
                new Paragraph(
                        "NexusHR Payslip"
                )
        );

        document.add(
                new Paragraph(
                        "Employee ID: "
                                + employeeId
                )
        );

        document.add(
                new Paragraph(
                        "Salary: ₹50,000"
                )
        );

        document.close();

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=payslip.pdf"
                )
                .contentType(
                        MediaType.APPLICATION_PDF
                )
                .body(
                        out.toByteArray()
                );
    }
}