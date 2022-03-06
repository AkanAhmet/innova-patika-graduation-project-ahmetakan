package com.innova.loanapplicationservice.business.services.impl;

import com.innova.loanapplicationservice.business.dto.LoanApplicationDto;
import com.innova.loanapplicationservice.business.dto.SmsDto;
import com.innova.loanapplicationservice.data.entity.LoanApplicationEntity;
import com.innova.loanapplicationservice.data.repository.LoanApplicationRepository;

import com.innova.loanapplicationservice.business.services.LoanApplicationService;
import com.innova.loanapplicationservice.util.LoanApplicationEvaluator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation class of the {@link LoanApplicationService} interface.
 *
 * @author Ahmet AKAN
 */
@Service
@Transactional
@Log4j2

public class LoanApplicationServiceImpl implements LoanApplicationService {

    @Value("${sms.service.uri}")
    private String SMS_SERVICE_URI; //= "http://localhost:8766/api/sms-service/";


    @Value("${credit.score.service.uri}")
    private String CREDIT_SCORE_SERVICE_URI; //= "http://localhost:8764/api/credit-score/";

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RestTemplate restTemplate;


    // LIST LOAN APPLICATION HISTORY OF CUSTOMER
    @Override
    public List<LoanApplicationDto> findAllByIdentificationNumber(Long identificationNumber) {

        try {

            List<LoanApplicationDto> listDto = new ArrayList<>();
            Iterable<LoanApplicationEntity> loanApplicationEntities = loanApplicationRepository.findAllByIdentificationNumber(identificationNumber);

            for (LoanApplicationEntity entity : loanApplicationEntities) {
                LoanApplicationDto loanApplicationDto = EntityToDto(entity);
                listDto.add(loanApplicationDto);
            }
            log.info("Loan Application ServiceImpl - findAllByIdentificationNumber method succeed");
            return listDto;
        } catch (Exception exception) {
            log.error("Loan Application ServiceImpl - findAllByIdentificationNumber method failed "+exception.getMessage());
        }
        return null;
    }

    // EVALUATE and RECORD LOAN APPLICATION
    @Override
    @Transactional
    public LoanApplicationDto evaluateAndRecordLoanApplication(LoanApplicationDto loanApplicationDto) {

        LoanApplicationDto evaluatedResultWithDatedTo = null;
        try {
            /** Learning customer credit score from credit-score-service */
            int creditScore = restTemplate.getForObject(CREDIT_SCORE_SERVICE_URI + '/' + loanApplicationDto.getIdentificationNumber(), Integer.class);
            loanApplicationDto.setCreditScore(creditScore);


            LoanApplicationEvaluator loanApplicationEvaluator = new LoanApplicationEvaluator();
            Map<String, Double> result = loanApplicationEvaluator.evaluateLoanApplication(creditScore, loanApplicationDto.getSalary());
            Map.Entry<String, Double> resultEntries = result.entrySet().iterator().next();
            loanApplicationDto.setLoanApplicationStatus(resultEntries.getKey());
            loanApplicationDto.setLoanLimit(resultEntries.getValue());


            LoanApplicationEntity loanApplicationEntity = DtoToEntity(loanApplicationDto);
            LoanApplicationEntity loanApplicationEntity1 = loanApplicationRepository.save(loanApplicationEntity);

            /** This step is implemented for take loanApplicationDate from Mongo Db  */
            evaluatedResultWithDatedTo = EntityToDto(loanApplicationEntity1);

            /** Trying to send sms to customer via sms-service and set result as smsStatus of customer */
            SmsDto smsDto = SmsDto.builder()
                    .phoneNumber(evaluatedResultWithDatedTo.getPhoneNumber())
                    .firstName(evaluatedResultWithDatedTo.getFirstName())
                    .lastname(evaluatedResultWithDatedTo.getLastName())
                    .loanApplicationDate(evaluatedResultWithDatedTo.getLoanApplicationDate())
                    .loanLimit(evaluatedResultWithDatedTo.getLoanLimit())
                    .loanApplicationStatus(evaluatedResultWithDatedTo.getLoanApplicationStatus())
                    .build();
            Boolean smsStatus = restTemplate.postForObject(SMS_SERVICE_URI, smsDto, Boolean.class);

            /** BAD PRACTISE, we have to know loan application date to send sms. So we saved entity before,
             * now we sent sms ent knowing sms status, we have to update last entity. */
            LoanApplicationEntity loanApplicationEntity2 = loanApplicationEntity1;
            loanApplicationEntity2.setSmsStatus(smsStatus);
            loanApplicationRepository.save(loanApplicationEntity2);
            /** */
            evaluatedResultWithDatedTo.setSmsStatus(smsStatus);

            log.info("Loan Application ServiceImpl - evaluateAndRecordLoanApplication method succeed");

        } catch (Exception exception) {
            log.error("Loan Application ServiceImpl - evaluateAndRecordLoanApplication method failed\t" + exception.getMessage());
            return null;
        }
        return evaluatedResultWithDatedTo;

    }

    ////Model Mapper Entity ==> Dto
    @Override
    public LoanApplicationDto EntityToDto(LoanApplicationEntity loanApplicationEntity) {
        LoanApplicationDto loanApplicationDto = modelMapper.map(loanApplicationEntity, LoanApplicationDto.class);
        return loanApplicationDto;
    }

    //Model Mapper Dto  ==> Entity
    @Override
    public LoanApplicationEntity DtoToEntity(LoanApplicationDto loanApplicationDto) {
        LoanApplicationEntity loanApplicationEntity = modelMapper.map(loanApplicationDto, LoanApplicationEntity.class);
        return loanApplicationEntity;
    }
}
