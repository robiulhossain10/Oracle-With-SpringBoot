package com.oracle.oraclewithspringboot.dtos;

import com.oracle.oraclewithspringboot.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequest {
    private String name;
    private String roll;
    private String fathersName;
    private String mothersName;
    private String address;
    private Status status;

}
