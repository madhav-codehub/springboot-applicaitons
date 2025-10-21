package com.codehub.address.controller;

import com.codehub.address.dto.AddressDTO;
import com.codehub.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<AddressDTO>> getAddressesByEmployeeId(@PathVariable Long employeeId) {
        List<AddressDTO> addresses = addressService.getAddressesByEmployeeId(employeeId);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }
}
