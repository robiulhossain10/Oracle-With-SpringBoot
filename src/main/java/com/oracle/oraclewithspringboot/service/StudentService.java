package com.oracle.oraclewithspringboot.service;

import com.oracle.oraclewithspringboot.dtos.StudentRequest;
import com.oracle.oraclewithspringboot.dtos.StudentResponse;
import com.oracle.oraclewithspringboot.entity.Student;
import com.oracle.oraclewithspringboot.enums.Status;
import com.oracle.oraclewithspringboot.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;


    public StudentResponse create(StudentRequest studentRequest){

       Student student = new Student();
        student.setName(studentRequest.getName());
        student.setFathersName(studentRequest.getFathersName());
        student.setMothersName(studentRequest.getMothersName());
        student.setRoll(studentRequest.getRoll());
        student.setAddress(studentRequest.getAddress());
        student.setStatus(studentRequest.getStatus());

Student saveST = studentRepository.save(student);

return toDTO(saveST);

    }

    public StudentResponse updateStatus(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));


        Student updatedStudent = studentRepository.save(student);

        return toDTO(updatedStudent);
    }


    public StudentResponse update(Long id,StudentRequest studentRequest){

        Student student = studentRepository.findById(id).orElseThrow();
        student.setName(studentRequest.getName());
        student.setFathersName(studentRequest.getFathersName());
        student.setMothersName(studentRequest.getMothersName());
        student.setRoll(studentRequest.getRoll());
        student.setAddress(studentRequest.getAddress());
        student.setStatus(studentRequest.getStatus());

        Student updateST = studentRepository.save(student);

        return toDTO(updateST);

    }

    public ResponseEntity<?> deletes(Long id){
        Student student = studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student id not found"+ id));
        studentRepository.delete(student);

       return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    public List<StudentResponse> getAll(){
        List<StudentResponse> st = studentRepository.findAll()

        .stream().map(this::mapToResponse).collect(Collectors.toList());
        return st;
    }



    public StudentResponse toDTO(Student st){
        return StudentResponse.builder()
                .ID(st.getId())
                .NAME(st.getName())
                .FATHERS_NAME(st.getFathersName())
                .MOTHERS_NAME(st.getMothersName())
                .ROLL(st.getRoll())
                .ADDRESS(st.getAddress())
                .STATUS(st.getStatus())
                .build();
    }


public StudentResponse mapToResponse(Student student){
        StudentResponse studentResponse = new StudentResponse();

        studentResponse.setID(student.getId());
        studentResponse.setNAME(student.getName());
        studentResponse.setFATHERS_NAME(student.getFathersName());
        studentResponse.setMOTHERS_NAME(student.getMothersName());
        studentResponse.setROLL(student.getRoll());
        studentResponse.setADDRESS(student.getAddress());
        studentResponse.setSTATUS(student.getStatus());

        return studentResponse;
}
}
