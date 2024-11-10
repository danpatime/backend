package com.example.api.contract.controller.domain;

import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContractDTO {
    private BusinessInfoDTO businessInfo;
    private EmployeeInfoDTO employeeInfo;
}
