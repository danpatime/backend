package com.example.api.contracts.dto;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInfoDTO {
    private String employeeName;
    private String employeePhone;
    private float starPoint;
    private int workCount;
}
