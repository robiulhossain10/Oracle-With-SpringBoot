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
        student.setStatus(Status.INACTIVE);

Student saveST = studentRepository.save(student);

return toDTO(saveST);

    }

    public StudentResponse updateStatus(Long id, Status status) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        student.setStatus(status);  // শুধু status update

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
                .id(st.getId())
                .name(st.getName())
                .fathersName(st.getFathersName())
                .mothersName(st.getMothersName())
                .roll(st.getRoll())
                .address(st.getAddress())
                .status(st.getStatus())
                .build();
    }


public StudentResponse mapToResponse(Student student){
        StudentResponse studentResponse = new StudentResponse();

        studentResponse.setId(student.getId());
        studentResponse.setName(student.getName());
        studentResponse.setFathersName(student.getFathersName());
        studentResponse.setMothersName(student.getMothersName());
        studentResponse.setRoll(student.getRoll());
        studentResponse.setAddress(student.getAddress());
        studentResponse.setStatus(student.getStatus());

        return studentResponse;
}
}
