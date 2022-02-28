package com.innova.frontend.business.services;

import com.innova.frontend.business.dto.CustomerDto;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    CustomerDto findCustomer(Long identificationNumber);
    ResponseEntity<CustomerDto> save(CustomerDto consumer);
    void delete(long identificationNumber);
    void update(CustomerDto consumer);
}
