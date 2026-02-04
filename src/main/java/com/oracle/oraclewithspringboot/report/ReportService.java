package com.oracle.oraclewithspringboot.report;

import com.oracle.oraclewithspringboot.dtos.StudentResponse;
import com.oracle.oraclewithspringboot.entity.Student;
import com.oracle.oraclewithspringboot.repository.StudentRepository;
import com.oracle.oraclewithspringboot.service.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final StudentRepository studentRepository;
    private final StudentService studentService;
//    @Transactional
//    public JasperPrint studentsPrint() throws Exception {
//
//        InputStream reportStream =
//                new ClassPathResource("reports/students.jrxml").getInputStream();
//
//        JasperReport jasperReport =
//                JasperCompileManager.compileReport(reportStream);
//
//        List<Student> students = studentRepository.findAllWithRelations();
//
//        if (students.isEmpty()) {
//            throw new RuntimeException("No Students data found");
//        }
//
//        List<StudentResponse> data = students.stream()
//                .map(a -> StudentResponse.builder()
//                        .ID(a.getId())
//                        .NAME(a.getName())
//                        .ROLL(a.getRoll())
//                        .FATHERS_NAME(a.getFathersName())
//                        .MOTHERS_NAME(a.getMothersName())
//                        .ADDRESS(a.getAddress())
//                        .STATUS(a.getStatus())
//
//                        .build())
//                .collect(Collectors.toList());
//
//        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(data);
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("createdBy", "Robiul Hossain");
//
//        return JasperFillManager.fillReport(jasperReport, params, ds);
//    }

    public byte[] generateStudentReportFromDb() {

        try {
            // 1️⃣ DB থেকে data
            List<StudentResponse> students = studentService.getAll();

            // 2️⃣ jrxml compile
            JasperReport jasperReport =
                    JasperCompileManager.compileReport(
                            getClass().getResourceAsStream("/reports/students.jrxml")
                    );

            // 3️⃣ datasource
            JRBeanCollectionDataSource dataSource =
                    new JRBeanCollectionDataSource(students);

            // 4️⃣ parameters (optional)
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("reportTitle", "Student Report");

            // 5️⃣ fill report
            JasperPrint jasperPrint =
                    JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // 6️⃣ export pdf
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate student report", e);
        }
    }
}
