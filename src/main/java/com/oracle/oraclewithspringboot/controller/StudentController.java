package com.oracle.oraclewithspringboot.controller;

import com.oracle.oraclewithspringboot.dtos.StudentRequest;
import com.oracle.oraclewithspringboot.dtos.StudentResponse;
import com.oracle.oraclewithspringboot.entity.Student;
import com.oracle.oraclewithspringboot.enums.Status;
import com.oracle.oraclewithspringboot.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public StudentResponse create(@RequestBody StudentRequest student){
        return studentService.create(student);
    }

    @GetMapping
    public List<StudentResponse> allStudents(){
        return studentService.getAll();
    }

    @PutMapping("/{id}")
    public StudentResponse updateStudent(@PathVariable Long id,@RequestBody StudentRequest studentRequest){
        return studentService.update(id,studentRequest);
    }

    @PatchMapping("/{id}")
    public StudentResponse updateStatuses(@PathVariable Long id, @RequestParam Status status){
        return studentService.updateStatus(id,status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        studentService.deletes(id);

        Map<String,Object> response = new HashMap<>();

        response.put("Message", "Student Deleted Success");
        response.put("id", id);

        return ResponseEntity.ok(response);
    }
}
