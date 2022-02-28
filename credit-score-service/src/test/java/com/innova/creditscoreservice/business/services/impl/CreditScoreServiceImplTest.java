package com.innova.creditscoreservice.business.services.impl;

import com.innova.creditscoreservice.business.dto.CreditScoreDto;
import com.innova.creditscoreservice.data.entity.CreditScoreEntity;
import com.innova.creditscoreservice.data.repository.CreditScoreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditScoreServiceImplTest {

    @Mock
    private CreditScoreRepository mockCreditScoreRepository;
    @Mock
    private ModelMapper mockModelMapper;

    @InjectMocks
    private CreditScoreServiceImpl creditScoreServiceImplUnderTest;

    @Test
    void testFindCreditScoreOfCustomer() {
        // Setup
        // Configure CreditScoreRepository.findCreditScoreOfCustomerByIdentificationNumber(...).
        final Optional<CreditScoreEntity> creditScoreEntity = Optional.of(new CreditScoreEntity("id", 0L, 0));
        when(mockCreditScoreRepository.findCreditScoreOfCustomerByIdentificationNumber(0L)).thenReturn(creditScoreEntity);

        // Run the test
        final Integer result = creditScoreServiceImplUnderTest.findCreditScoreOfCustomer(0L);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testSaveCreditScore_CreditScoreRepositoryFindCreditScoreOfCustomerByIdentificationNumberReturnsAbsent() {
        // Setup
        final CreditScoreDto creditScoreDto = new CreditScoreDto("id", 0L, 0);
        final CreditScoreDto expectedResult = new CreditScoreDto("id", 0L, 0);
        when(mockCreditScoreRepository.findCreditScoreOfCustomerByIdentificationNumber(0L)).thenReturn(Optional.empty());
        when(mockModelMapper.map(new CreditScoreDto("id", 0L, 0), CreditScoreEntity.class)).thenReturn(new CreditScoreEntity("id", 0L, 0));
        when(mockCreditScoreRepository.save(new CreditScoreEntity("id", 0L, 0))).thenReturn(new CreditScoreEntity("id", 0L, 0));

        // Run the test
        final CreditScoreDto result = creditScoreServiceImplUnderTest.saveCreditScore(creditScoreDto);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockCreditScoreRepository).save(new CreditScoreEntity("id", 0L, 0));
    }

    @Test
    void testFindCreditScoreOfCustomerByIdentificationNumber() {
        // Setup
        // Configure CreditScoreRepository.findCreditScoreOfCustomerByIdentificationNumber(...).
        final Optional<CreditScoreEntity> creditScoreEntity = Optional.of(new CreditScoreEntity("id", 0L, 0));
        when(mockCreditScoreRepository.findCreditScoreOfCustomerByIdentificationNumber(0L)).thenReturn(creditScoreEntity);

        // Run the test
        final boolean result = creditScoreServiceImplUnderTest.findCreditScoreOfCustomerByIdentificationNumber(0L);

        // Verify the results
        assertThat(result).isTrue();
    }


    @Test
    void testEntityToDto() {
        // Setup
        final CreditScoreEntity creditScoreEntity = new CreditScoreEntity("id", 0L, 0);
        final CreditScoreDto expectedResult = new CreditScoreDto("id", 0L, 0);
        when(mockModelMapper.map(new CreditScoreEntity("id", 0L, 0), CreditScoreDto.class)).thenReturn(new CreditScoreDto("id", 0L, 0));

        // Run the test
        final CreditScoreDto result = creditScoreServiceImplUnderTest.EntityToDto(creditScoreEntity);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDtoToEntity() {
        // Setup
        final CreditScoreDto creditScoreDto = new CreditScoreDto("id", 0L, 0);
        final CreditScoreEntity expectedResult = new CreditScoreEntity("id", 0L, 0);
        when(mockModelMapper.map(new CreditScoreDto("id", 0L, 0), CreditScoreEntity.class)).thenReturn(new CreditScoreEntity("id", 0L, 0));

        // Run the test
        final CreditScoreEntity result = creditScoreServiceImplUnderTest.DtoToEntity(creditScoreDto);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
