package com.innova.customerservice.business.services.impl;

import com.innova.customerservice.business.dto.CustomerDto;
import com.innova.customerservice.business.services.CustomerService;
import com.innova.customerservice.exception.CustomerAlreadyExistsException;
import com.innova.customerservice.exception.CustomerNotExistException;
import com.innova.customerservice.data.entity.CustomerEntity;
import com.innova.customerservice.data.repository.CustomerRepository;
import com.innova.customerservice.exception.InvalidIdException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation class of the {@link CustomerService} interface.
 *
 * @author Ahmet AKAN
 */

@Service
@Transactional
@Log4j2

public class CustomerServiceImpl implements CustomerService {

    @Value("${credit.score.service.uri}")
    private String CREDIT_SCORE_SERVICE_URI; //= "http://localhost:8764/api/credit-score/";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;


    // CREATE CUSTOMER
    @Override
    @Transactional

    public CustomerDto createCustomer(CustomerDto customerDto) {

        // Check for Customer is exits already
        if (checkForCustomerExists(customerDto.getIdentificationNumber())) {
            throw new CustomerAlreadyExistsException("Customer already exists with the given identification number\t" + customerDto.getIdentificationNumber());
        }
        // Check for identification number is valid
        if (!identificationNumberIsValid(customerDto.getIdentificationNumber())) {
            throw new InvalidIdException("Customer Identification Number is invalid, please check for it");
        }

        CustomerEntity customerEntity = DtoToEntity(customerDto);
        customerRepository.save(customerEntity);
        /** Communication with Credit Score Service for initializing customer credit score.*/
        String creditScoreObject = restTemplate.postForObject(CREDIT_SCORE_SERVICE_URI + '/' + customerDto.getIdentificationNumber(), null, String.class);

        return customerDto;
    }

    // FIND CUSTOMER
    @Override
    @Transactional
    public CustomerDto findCustomerByIdentificationNumber(Long identificationNumber) {
        CustomerEntity customerEntity =
                customerRepository.findCustomerByIdentificationNumber(identificationNumber)
                        .orElseThrow(() -> new CustomerNotExistException("Could not found customer with the given identification number\t" + identificationNumber));

        CustomerDto customerDto = EntityToDto(customerEntity);
        return customerDto;
    }


    // UPDATE CUSTOMER
    @Override
    @Transactional
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        if (!checkForCustomerExists(customerDto.getIdentificationNumber())) {
            throw new CustomerNotExistException("Could not found customer with the given identification number\t" + customerDto.getIdentificationNumber());
        }
        Optional<CustomerEntity> optionalEntity = customerRepository.findCustomerByIdentificationNumber(customerDto.getIdentificationNumber());
        CustomerEntity customerEntity = optionalEntity.get();
        customerEntity.setFirstName(customerDto.getFirstName());
        customerEntity.setLastName(customerDto.getLastName());
        customerEntity.setSalary(customerDto.getSalary());
        customerEntity.setPhoneNumber(customerDto.getPhoneNumber());
        customerRepository.save(customerEntity);
        return customerDto;
    }

    // DELETE CUSTOMER
    @Override
    @Transactional
    public void deleteCustomerByIdentificationNumber(Long identificationNumber) {
        if (!checkForCustomerExists(identificationNumber)) {
            throw new CustomerNotExistException("Could not found customer with the given identification number\t" + identificationNumber);
        }
        customerRepository.deleteByIdentificationNumber(identificationNumber);
    }

    // FIND IF CUSTOMER EXITS
    private boolean checkForCustomerExists(Long identificationNumber) {
        log.info("Looking if the customer with the following fields exists: " +
                "\nID= " + identificationNumber);
        return customerRepository.findCustomerByIdentificationNumber(identificationNumber).isPresent();
    }

    public boolean identificationNumberIsValid(Long identificationNumber) {
        String id = String.valueOf(identificationNumber);
        if (id.length() == 11 && identificationNumber % 2 == 0)
            return true;
        return false;
    }

    ////Model Mapper Entity ==> Dto
    @Override
    public CustomerDto EntityToDto(CustomerEntity customerEntity) {
        CustomerDto customerDto = modelMapper.map(customerEntity, CustomerDto.class);
        return customerDto;
    }

    //Model Mapper Dto  ==> Entity
    @Override
    public CustomerEntity DtoToEntity(CustomerDto customerDto) {
        CustomerEntity customerEntity = modelMapper.map(customerDto, CustomerEntity.class);
        return customerEntity;
    }

}
