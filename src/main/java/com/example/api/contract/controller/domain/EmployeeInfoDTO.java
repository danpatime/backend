package com.example.api.contract.controller.domain;

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
