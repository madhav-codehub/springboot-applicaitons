package com.codehub.employee.service;

import com.codehub.employee.dto.AddressDTO;
import com.codehub.employee.dto.EmployeeDTO;
import com.codehub.employee.entity.Employee;
import com.codehub.employee.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RestTemplate restTemplate;

    @Value("${address.service.url}")
    private String addressServiceUrl;

    public EmployeeDTO getEmployeeWithAddresses(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Employee not found!!")
        );
        String addressUrl = addressServiceUrl + "/addresses/employee/" + id;
        AddressDTO[] addressArray = restTemplate.getForObject(addressUrl, AddressDTO[].class);
        List<AddressDTO> addresses = Arrays.asList(addressArray != null ? addressArray : new AddressDTO[0]);

        //convert to employee dto
        return EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .addresses(addresses)
                .build();

    }


}
