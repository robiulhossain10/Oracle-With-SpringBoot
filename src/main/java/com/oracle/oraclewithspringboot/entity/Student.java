package com.oracle.oraclewithspringboot.entity;

import com.oracle.oraclewithspringboot.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "STUDENT")

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(name = "student_seq", sequenceName = "STUDENT_SEQ", allocationSize = 1)
    private Long id;

    private String name;
    private String roll;
    private String fathersName;
    private String mothersName;
    private String address;
    private String status;
}
