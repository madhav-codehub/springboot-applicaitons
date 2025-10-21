package com.codehub.address.repository;

import com.codehub.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByEmployeeId(Long employeeId);
}
