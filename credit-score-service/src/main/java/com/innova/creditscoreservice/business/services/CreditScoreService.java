package com.innova.creditscoreservice.business.services;

import com.innova.creditscoreservice.business.dto.CreditScoreDto;
import com.innova.creditscoreservice.data.entity.CreditScoreEntity;

public interface CreditScoreService {

    Integer findCreditScoreOfCustomer(Long identificationNumber);
    CreditScoreDto saveCreditScore(CreditScoreDto creditScoreDto);
    CreditScoreDto EntityToDto(CreditScoreEntity creditScoreEntity);
    CreditScoreEntity DtoToEntity(CreditScoreDto creditScoreDto);

}
