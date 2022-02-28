package com.innova.customerservice.business.services;

import com.innova.customerservice.business.dto.CustomerDto;
import com.innova.customerservice.data.entity.CustomerEntity;
import java.util.List;

public interface CustomerService {

    //CRUD
    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerDto findCustomerByIdentificationNumber(Long identificationNumber);
    CustomerDto updateCustomer(CustomerDto customerDto);
    void deleteCustomerByIdentificationNumber(Long identificationNumber);
    CustomerDto EntityToDto(CustomerEntity customerEntity);
    CustomerEntity DtoToEntity(CustomerDto customerDto);

}
