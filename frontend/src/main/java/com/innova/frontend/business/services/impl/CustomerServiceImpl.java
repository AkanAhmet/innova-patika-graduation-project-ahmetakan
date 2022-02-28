package com.innova.frontend.business.services.impl;

import com.innova.frontend.business.dto.CustomerDto;
import com.innova.frontend.business.services.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation class of the {@link CustomerService} interface.
 *
 * @author Ahmet AKAN
 */

@Service
@Log4j2
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private RestTemplate restTemplate;


    @Value("${customer.service.uri}")
    public String CUSTOMER_SERVICE_URI;

    // FIND CUSTOMER FROM CUSTOMER-SERVICE
    @Override
    public CustomerDto findCustomer(Long identificationNumber) {
        CustomerDto customerDto = restTemplate.getForEntity(CUSTOMER_SERVICE_URI + '/' + identificationNumber, CustomerDto.class).getBody();
        log.info("Frontend GET request to customer-service is succeed \t" + customerDto);
        return customerDto;
    }

    // SAVE CUSTOMER TO CUSTOMER-SERVICE
    @Override
    public ResponseEntity<CustomerDto> save(CustomerDto customerDto) {
        ResponseEntity<CustomerDto> customerDtoResponseEntity = restTemplate.postForEntity(CUSTOMER_SERVICE_URI, customerDto, CustomerDto.class);
        log.info("Frontend POST request to customer-service is succeed \t" + customerDtoResponseEntity);
        return customerDtoResponseEntity;
    }

    // DELETE CUSTOMER FROM CUSTOMER-SERVICE
    @Override
    public void delete(long identificationNumber) {
        restTemplate.delete(CUSTOMER_SERVICE_URI + '/' + identificationNumber);
        log.info("Frontend DELETE request to customer-service is succeed ");

    }

    // UPDATE CUSTOMER FROM CUSTOMER-SERVICE
    @Override
    public void update(CustomerDto customerDto) {
        restTemplate.put(CUSTOMER_SERVICE_URI + '/' + customerDto.getIdentificationNumber(), customerDto);
        log.info("Frontend UPDATE request to customer-service is succeed ");

    }
}
