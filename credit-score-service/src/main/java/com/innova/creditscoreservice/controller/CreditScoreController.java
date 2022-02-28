package com.innova.creditscoreservice.controller;

import com.innova.creditscoreservice.business.dto.CreditScoreDto;
import com.innova.creditscoreservice.business.services.CreditScoreService;
import com.innova.creditscoreservice.util.CreditScoreGeneratorFromId;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Base controller class for all credit score operations.
 *
 * @author Ahmet AKAN
 */

@RestController
@RequestMapping("/api/credit-score")
@Log4j2
public class CreditScoreController {

    @Autowired
    private CreditScoreService creditScoreService;

    CreditScoreGeneratorFromId creditScoreGeneratorFromId;

    //SAVE Credit Score of Customer
    //http://localhost:8764/api/credit-score/12345678912
    @PostMapping("/{identificationNumber}")
    public ResponseEntity<String> createCreditScore(@PathVariable Long identificationNumber) {

        creditScoreGeneratorFromId = new CreditScoreGeneratorFromId();
        int creditScoreOfCustomer = creditScoreGeneratorFromId.generateNewCreditScore(identificationNumber);
        CreditScoreDto creditScoreDto =
                CreditScoreDto.builder()
                        .creditScore(creditScoreOfCustomer)
                        .identificationNumber(identificationNumber)
                        .build();
        try {
            creditScoreService.saveCreditScore(creditScoreDto);
            log.info("Credit Score created successfully from createCreditScore-Controller");

        } catch (Exception exception) {
            log.error("Credit Score creating faied from createCreditScore-Controller");
            return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Credit Score of Customer with id " + identificationNumber + "saved successfully.");
    }


    // FIND CREDIT SCORE OF CUSTOMER
    //http://localhost:8764/api/credit-score/30815138278
    @GetMapping("{identificationNumber}")
    public ResponseEntity<Integer> findCreditScoreOfCustomer(@PathVariable Long identificationNumber) {
        return ResponseEntity.ok(creditScoreService.findCreditScoreOfCustomer(identificationNumber));

    }
}
