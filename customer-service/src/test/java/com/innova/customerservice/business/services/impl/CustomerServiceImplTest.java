package com.innova.customerservice.business.services.impl;

import com.innova.customerservice.business.dto.CustomerDto;
import com.innova.customerservice.data.entity.CustomerEntity;
import com.innova.customerservice.data.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private RestTemplate mockRestTemplate;
    @Mock
    private CustomerRepository mockCustomerRepository;
    @Mock
    private ModelMapper mockModelMapper;

    @InjectMocks
    private CustomerServiceImpl customerServiceImplUnderTest;


    @Test
    void testFindCustomerByIdentificationNumber() {
        // Setup
        final CustomerDto expectedResult = new CustomerDto(0L, 0L, "firstName", "lastName", 0, "phoneNumber");

        // Configure CustomerRepository.findCustomerByIdentificationNumber(...).
        final Optional<CustomerEntity> customerEntity = Optional.of(new CustomerEntity(0L, 0L, "firstName", "lastName", 0, "phoneNumber"));
        when(mockCustomerRepository.findCustomerByIdentificationNumber(0L)).thenReturn(customerEntity);

        // Configure ModelMapper.map(...).
        final CustomerDto customerDto = new CustomerDto(0L, 0L, "firstName", "lastName", 0, "phoneNumber");
        when(mockModelMapper.map(new CustomerEntity(0L, 0L, "firstName", "lastName", 0, "phoneNumber"), CustomerDto.class)).thenReturn(customerDto);

        // Run the test
        final CustomerDto result = customerServiceImplUnderTest.findCustomerByIdentificationNumber(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }



    @Test
    void testUpdateCustomer() {
        // Setup
        final CustomerDto customerDto = new CustomerDto(0L, 0L, "firstName", "lastName", 0, "phoneNumber");
        final CustomerDto expectedResult = new CustomerDto(0L, 0L, "firstName", "lastName", 0, "phoneNumber");

        // Configure CustomerRepository.findCustomerByIdentificationNumber(...).
        final Optional<CustomerEntity> customerEntity = Optional.of(new CustomerEntity(0L, 0L, "firstName", "lastName", 0, "phoneNumber"));
        when(mockCustomerRepository.findCustomerByIdentificationNumber(0L)).thenReturn(customerEntity);

        // Configure CustomerRepository.save(...).
        final CustomerEntity customerEntity1 = new CustomerEntity(0L, 0L, "firstName", "lastName", 0, "phoneNumber");
        when(mockCustomerRepository.save(new CustomerEntity(0L, 0L, "firstName", "lastName", 0, "phoneNumber"))).thenReturn(customerEntity1);

        // Run the test
        final CustomerDto result = customerServiceImplUnderTest.updateCustomer(customerDto);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockCustomerRepository).save(new CustomerEntity(0L, 0L, "firstName", "lastName", 0, "phoneNumber"));
    }


    @Test
    void testDeleteCustomerByIdentificationNumber() {
        // Setup
        // Configure CustomerRepository.findCustomerByIdentificationNumber(...).
        final Optional<CustomerEntity> customerEntity = Optional.of(new CustomerEntity(0L, 0L, "firstName", "lastName", 0, "phoneNumber"));
        when(mockCustomerRepository.findCustomerByIdentificationNumber(0L)).thenReturn(customerEntity);

        // Run the test
        customerServiceImplUnderTest.deleteCustomerByIdentificationNumber(0L);

        // Verify the results
        verify(mockCustomerRepository).deleteByIdentificationNumber(0L);
    }



    @Test
    void testIdentificationNumberIsValid() {
        assertThat(customerServiceImplUnderTest.identificationNumberIsValid(30815138278L)).isTrue();
    }

    @Test
    void testEntityToDto() {
        // Setup
        final CustomerEntity customerEntity = new CustomerEntity(0L, 0L, "firstName", "lastName", 0, "phoneNumber");
        final CustomerDto expectedResult = new CustomerDto(0L, 0L, "firstName", "lastName", 0, "phoneNumber");

        // Configure ModelMapper.map(...).
        final CustomerDto customerDto = new CustomerDto(0L, 0L, "firstName", "lastName", 0, "phoneNumber");
        when(mockModelMapper.map(new CustomerEntity(0L, 0L, "firstName", "lastName", 0, "phoneNumber"), CustomerDto.class)).thenReturn(customerDto);

        // Run the test
        final CustomerDto result = customerServiceImplUnderTest.EntityToDto(customerEntity);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDtoToEntity() {
        // Setup
        final CustomerDto customerDto = new CustomerDto(0L, 0L, "firstName", "lastName", 0, "phoneNumber");
        final CustomerEntity expectedResult = new CustomerEntity(0L, 0L, "firstName", "lastName", 0, "phoneNumber");

        // Configure ModelMapper.map(...).
        final CustomerEntity customerEntity = new CustomerEntity(0L, 0L, "firstName", "lastName", 0, "phoneNumber");
        when(mockModelMapper.map(new CustomerDto(0L, 0L, "firstName", "lastName", 0, "phoneNumber"), CustomerEntity.class)).thenReturn(customerEntity);

        // Run the test
        final CustomerEntity result = customerServiceImplUnderTest.DtoToEntity(customerDto);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
