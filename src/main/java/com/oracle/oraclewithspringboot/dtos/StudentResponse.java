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
    private Long ID;

    private String NAME;
    private String ROLL;
    private String FATHERS_NAME;
    private String MOTHERS_NAME;
    private String ADDRESS;
    private String STATUS;
}
