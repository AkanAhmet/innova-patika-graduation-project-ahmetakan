package com.innova.loanapplicationservice.data.repository;

import com.innova.loanapplicationservice.data.entity.LoanApplicationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanApplicationRepository extends MongoRepository<LoanApplicationEntity,String> {


    List<LoanApplicationEntity> findAllByIdentificationNumber(Long identificationNumber);
}
