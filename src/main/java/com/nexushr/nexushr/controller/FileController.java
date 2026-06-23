package com.nexushr.nexushr.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
@CrossOrigin("*")
public class FileController {

    // UPLOAD FILE
	@PostMapping("/upload")
	public String uploadFile(
	        @RequestParam("file") MultipartFile file) {

	    try {

	        // CREATE uploads FOLDER
	        String uploadDir =
	                System.getProperty("user.dir")
	                + "/uploads/";

	        File directory =
	                new File(uploadDir);

	        if (!directory.exists()) {

	            directory.mkdirs();
	        }

	        // FILE PATH
	        String filePath =
	                uploadDir + file.getOriginalFilename();

	        // SAVE FILE
	        file.transferTo(
	                new File(filePath)
	        );

	        return "File Uploaded Successfully";

	    } catch (Exception e) {

	        e.printStackTrace();

	        return "File Upload Failed : "
	                + e.getMessage();
	    }
	}

    // DOWNLOAD FILE
    @GetMapping("/download/{fileName}")
    public ResponseEntity<InputStreamResource>
    downloadFile(
            @PathVariable String fileName)
            throws IOException {

        String filePath =
                "uploads/" + fileName;

        File file =
                new File(filePath);

        InputStreamResource resource =
                new InputStreamResource(
                        new FileInputStream(file)
                );

        return ResponseEntity.ok()

                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" +
                                file.getName()
                )

                .contentType(
                        MediaType.APPLICATION_OCTET_STREAM
                )

                .contentLength(file.length())

                .body(resource);
    }
}