package com.innova.loanapplicationservice.business.services;

import com.innova.loanapplicationservice.business.dto.LoanApplicationDto;
import com.innova.loanapplicationservice.data.entity.LoanApplicationEntity;

import java.util.List;

public interface LoanApplicationService {

    List<LoanApplicationDto> findAllByIdentificationNumber(Long identificationNumber);
    LoanApplicationDto evaluateAndRecordLoanApplication(LoanApplicationDto loanApplicationDto);
    LoanApplicationDto EntityToDto(LoanApplicationEntity loanApplicationEntity);
    LoanApplicationEntity DtoToEntity(LoanApplicationDto evaluateAndRecordLoanApplicationsDto);
}
