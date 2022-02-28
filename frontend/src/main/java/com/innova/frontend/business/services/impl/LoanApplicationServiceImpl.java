package com.innova.frontend.business.services.impl;

import com.innova.frontend.business.dto.LoanApplicationDto;
import com.innova.frontend.business.services.LoanApplicationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Implementation class of the {@link LoanApplicationService} interface.
 *
 * @author Ahmet AKAN
 */

@Service
@Log4j2
public class LoanApplicationServiceImpl implements LoanApplicationService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${loan.application.service.uri}")
    public String LOAN_APPlICATION_URI;

    // SAVE LOAN APPLICATION TO LOAN-APPLICATION-SERVICE
    @Override
    public LoanApplicationDto postApplication(LoanApplicationDto loanApplicationDto) {
        LoanApplicationDto loanApplicationDto1 = restTemplate.postForObject(LOAN_APPlICATION_URI, loanApplicationDto, LoanApplicationDto.class);
        log.info("Frontend POST request to loan-application-service is succeed \t" + loanApplicationDto1);
        return loanApplicationDto1;

    }

    // GET LOAN APPLICATION HISTORY OF CUSTOMER FROM LOAN-APPLICATION-SERVICE
    @Override
    public List<LoanApplicationDto> getLogsByID(long identificationNumber) {
        List<LoanApplicationDto> loanApplicationDtos = restTemplate.getForObject(LOAN_APPlICATION_URI + '/' + identificationNumber, List.class);
        log.info("Frontend GET request to loan-application-service is succeed \t" + loanApplicationDtos.toString());
        return loanApplicationDtos;
    }
}
