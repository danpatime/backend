package com.example.api.contracts.dto;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContractDTO {
    private BusinessInfoDTO businessInfo;
    private EmployeeInfoDTO employeeInfo;
}
