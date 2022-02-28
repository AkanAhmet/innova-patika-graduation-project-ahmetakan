package com.innova.creditscoreservice.data.repository;

import com.innova.creditscoreservice.data.entity.CreditScoreEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface CreditScoreRepository extends MongoRepository<CreditScoreEntity,String> {
    Optional<CreditScoreEntity> findCreditScoreOfCustomerByIdentificationNumber(Long identificationNumber);
}
