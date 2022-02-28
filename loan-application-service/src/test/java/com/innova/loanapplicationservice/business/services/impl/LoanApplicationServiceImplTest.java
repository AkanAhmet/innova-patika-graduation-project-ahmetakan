package com.innova.loanapplicationservice.business.services.impl;

import com.innova.loanapplicationservice.business.dto.LoanApplicationDto;
import com.innova.loanapplicationservice.business.dto.SmsDto;
import com.innova.loanapplicationservice.data.entity.LoanApplicationEntity;
import com.innova.loanapplicationservice.data.repository.LoanApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanApplicationServiceImplTest {

    @Mock
    private LoanApplicationRepository mockLoanApplicationRepository;
    @Mock
    private ModelMapper mockModelMapper;
    @Mock
    private RestTemplate mockRestTemplate;

    @InjectMocks
    private LoanApplicationServiceImpl loanApplicationServiceImplUnderTest;

    @Test
    void testFindAllByIdentificationNumber() {
        // Setup
        final List<LoanApplicationDto> expectedResult = List.of(new LoanApplicationDto("id", 0L, "firstName", "lastName", 0, "phoneNumber", 0, 0.0, "loanApplicationStatus", "loanApplicationDate", false));

        // Configure LoanApplicationRepository.findAllByIdentificationNumber(...).
        final List<LoanApplicationEntity> loanApplicationEntities = List.of(new LoanApplicationEntity("id", 0L, "firstName", "lastName", 0.0, "phoneNumber", 0, 0.0, "loanApplicationStatus", "loanApplicationDate", false));
        when(mockLoanApplicationRepository.findAllByIdentificationNumber(0L)).thenReturn(loanApplicationEntities);

        // Configure ModelMapper.map(...).
        final LoanApplicationDto loanApplicationDto = new LoanApplicationDto("id", 0L, "firstName", "lastName", 0, "phoneNumber", 0, 0.0, "loanApplicationStatus", "loanApplicationDate", false);
        when(mockModelMapper.map(new LoanApplicationEntity("id", 0L, "firstName", "lastName", 0.0, "phoneNumber", 0, 0.0, "loanApplicationStatus", "loanApplicationDate", false), LoanApplicationDto.class)).thenReturn(loanApplicationDto);

        // Run the test
        final List<LoanApplicationDto> result = loanApplicationServiceImplUnderTest.findAllByIdentificationNumber(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }



    @Test
    void testEntityToDto() {
        // Setup
        final LoanApplicationEntity loanApplicationEntity = new LoanApplicationEntity("id", 0L, "firstName", "lastName", 0.0, "phoneNumber", 0, 0.0, "loanApplicationStatus", "loanApplicationDate", false);
        final LoanApplicationDto expectedResult = new LoanApplicationDto("id", 0L, "firstName", "lastName", 0, "phoneNumber", 0, 0.0, "loanApplicationStatus", "loanApplicationDate", false);

        // Configure ModelMapper.map(...).
        final LoanApplicationDto loanApplicationDto = new LoanApplicationDto("id", 0L, "firstName", "lastName", 0, "phoneNumber", 0, 0.0, "loanApplicationStatus", "loanApplicationDate", false);
        when(mockModelMapper.map(new LoanApplicationEntity("id", 0L, "firstName", "lastName", 0.0, "phoneNumber", 0, 0.0, "loanApplicationStatus", "loanApplicationDate", false), LoanApplicationDto.class)).thenReturn(loanApplicationDto);

        // Run the test
        final LoanApplicationDto result = loanApplicationServiceImplUnderTest.EntityToDto(loanApplicationEntity);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDtoToEntity() {
        // Setup
        final LoanApplicationDto loanApplicationDto = new LoanApplicationDto("id", 0L, "firstName", "lastName", 0, "phoneNumber", 0, 0.0, "loanApplicationStatus", "loanApplicationDate", false);
        final LoanApplicationEntity expectedResult = new LoanApplicationEntity("id", 0L, "firstName", "lastName", 0.0, "phoneNumber", 0, 0.0, "loanApplicationStatus", "loanApplicationDate", false);

        // Configure ModelMapper.map(...).
        final LoanApplicationEntity loanApplicationEntity = new LoanApplicationEntity("id", 0L, "firstName", "lastName", 0.0, "phoneNumber", 0, 0.0, "loanApplicationStatus", "loanApplicationDate", false);
        when(mockModelMapper.map(new LoanApplicationDto("id", 0L, "firstName", "lastName", 0, "phoneNumber", 0, 0.0, "loanApplicationStatus", "loanApplicationDate", false), LoanApplicationEntity.class)).thenReturn(loanApplicationEntity);

        // Run the test
        final LoanApplicationEntity result = loanApplicationServiceImplUnderTest.DtoToEntity(loanApplicationDto);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
