package com.codehub.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {
    private Long id;
    private Long employeeId;
    private String street;
    private String city;
    private String state;
}
