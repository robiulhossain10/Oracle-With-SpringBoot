package com.oracle.oraclewithspringboot.report;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class Controller {
    private final ReportService reportService;
    @GetMapping("/students/pdf")
    public ResponseEntity<byte[]> downloadStudentReport() {

        byte[] pdf = reportService.generateStudentReportFromDb();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=students.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
