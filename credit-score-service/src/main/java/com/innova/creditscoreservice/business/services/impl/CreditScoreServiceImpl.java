package com.innova.creditscoreservice.business.services.impl;

import com.innova.creditscoreservice.business.dto.CreditScoreDto;
import com.innova.creditscoreservice.business.services.CreditScoreService;
import com.innova.creditscoreservice.data.entity.CreditScoreEntity;
import com.innova.creditscoreservice.data.repository.CreditScoreRepository;
import com.innova.creditscoreservice.exception.CreditScoreNotExitsException;
import com.innova.creditscoreservice.util.CreditScoreGeneratorFromId;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Implementation class of the {@link CreditScoreService} interface.
 *
 * @author Ahmet AKAN
 */

@Service
@Transactional
@Log4j2
public class CreditScoreServiceImpl implements CreditScoreService {

    @Autowired
    private CreditScoreRepository creditScoreRepository;

    @Autowired
    private ModelMapper modelMapper;


    // FIND CREDIT SCORE OF CUSTOMER
    @Override
    public Integer findCreditScoreOfCustomer(Long identificationNumber) {
        CreditScoreEntity creditScoreEntity = creditScoreRepository.findCreditScoreOfCustomerByIdentificationNumber(identificationNumber)
                .orElseThrow(() -> new CreditScoreNotExitsException("Could not found customer with the given identification number\t"));
        Integer creditScore = creditScoreEntity.getCreditScore();
        return creditScore;
    }

    // SAVE CREDIT SCORE
    @Override
    @Transactional
    public CreditScoreDto saveCreditScore(CreditScoreDto creditScoreDto) {

        boolean alreadyExitsControl = findCreditScoreOfCustomerByIdentificationNumber(creditScoreDto.getIdentificationNumber());
        // Doesnt save the credit score if already exits.
        if (alreadyExitsControl) {
            return creditScoreDto;
        }
        // Generate credit score of customer from util
        CreditScoreGeneratorFromId creditScoreGeneratorFromId = new CreditScoreGeneratorFromId();

        creditScoreDto.setCreditScore(creditScoreGeneratorFromId.generateNewCreditScore(creditScoreDto.getIdentificationNumber()));
        CreditScoreEntity creditScoreEntity = DtoToEntity(creditScoreDto);
        creditScoreRepository.save(creditScoreEntity);
        return creditScoreDto;
    }

    // Check if Customer Credit Score Already Exits
    public boolean findCreditScoreOfCustomerByIdentificationNumber(Long identificationNumber) {
        log.info("Looking if the customer Credit Score with the following fields exists: " +
                "\nID= " + identificationNumber);
        return creditScoreRepository.findCreditScoreOfCustomerByIdentificationNumber(identificationNumber).isPresent();
    }

    ////Model Mapper Entity ==> Dto
    @Override
    public CreditScoreDto EntityToDto(CreditScoreEntity creditScoreEntity) {
        CreditScoreDto creditScoreDto = modelMapper.map(creditScoreEntity, CreditScoreDto.class);
        return creditScoreDto;
    }

    //Model Mapper Dto  ==> Entity
    @Override
    public CreditScoreEntity DtoToEntity(CreditScoreDto creditScoreDto) {
        CreditScoreEntity creditScoreEntity = modelMapper.map(creditScoreDto, CreditScoreEntity.class);
        return creditScoreEntity;
    }

}
