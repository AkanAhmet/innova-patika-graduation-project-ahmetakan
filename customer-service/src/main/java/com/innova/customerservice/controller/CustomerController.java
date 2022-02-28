package com.innova.customerservice.controller;

import com.innova.customerservice.business.dto.CustomerDto;
import com.innova.customerservice.business.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

/**
 * Base controller class for all customer operations.
 *
 * @author Ahmet AKAN
 */


@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    //SAVE Customer
    //http://localhost:8763/api/customers
    @PostMapping()
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody @Valid CustomerDto customerDto) {

        return ResponseEntity.ok(customerService.createCustomer(customerDto));
    }


    //FIND CUSTOMER
    // http://localhost:8763/api/customers/12345678912
    @GetMapping("/{identificationNumber}")
    public ResponseEntity<CustomerDto> findCustomerByIdentificationNumber(@PathVariable Long identificationNumber) {
        return ResponseEntity.ok(customerService.findCustomerByIdentificationNumber(identificationNumber));
    }


    //UPDATE CUSTOMER
    // http://localhost:8763/api/customers/12345678912
    @PutMapping("/{identificationNumber}")
    public ResponseEntity<String> updateCustomer(@PathVariable Long identificationNumber, @RequestBody CustomerDto customerDto) {
        customerService.updateCustomer(customerDto);
        return ResponseEntity.ok(String.format("Customer with the %d ID is successfully updated.", identificationNumber));
    }

    //DELETE CUSTOMER
    // http://localhost:8763/api/customers/12345678912
    @DeleteMapping("/{identificationNumber}")
    public ResponseEntity<String> deleteCustomerByIdentificationNumber(@PathVariable Long identificationNumber) {
        customerService.deleteCustomerByIdentificationNumber(identificationNumber);
        return ResponseEntity.ok(String.format(String.format("Customer with the %d ID is successfully deleted.", identificationNumber)));
    }


}
