package com.innova.frontend.business.services;

import com.innova.frontend.business.dto.LoanApplicationDto;
import java.util.List;

public interface LoanApplicationService {
    LoanApplicationDto postApplication(LoanApplicationDto loanApplicationDto);
    List<LoanApplicationDto> getLogsByID(long identificationNumber);
}
