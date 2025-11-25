package com.oracle.oraclewithspringboot.dtos;

import com.oracle.oraclewithspringboot.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private Long id;

    private String name;
    private String roll;
    private String fathersName;
    private String mothersName;
    private String address;
    private Status status;
}
