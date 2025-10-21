package com.codehub.address.service;

import com.codehub.address.dto.AddressDTO;
import com.codehub.address.entity.Address;
import com.codehub.address.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public List<AddressDTO> getAddressesByEmployeeId(Long employeeId) {
        List<Address> addressList = addressRepository.findByEmployeeId(employeeId);
        return addressList.stream().map(this::convertToDTO).toList();
    }

    private AddressDTO convertToDTO(Address address) {
        return AddressDTO.builder()
                .id(address.getId())
                .employeeId(address.getEmployeeId())
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .build();
    }

}
