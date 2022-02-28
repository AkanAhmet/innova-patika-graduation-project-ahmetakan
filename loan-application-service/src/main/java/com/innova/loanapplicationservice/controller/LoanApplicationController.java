package com.innova.loanapplicationservice.controller;

import com.innova.loanapplicationservice.business.dto.LoanApplicationDto;
import com.innova.loanapplicationservice.business.services.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Base controller class for all loan application operations.
 *
 * @author Ahmet AKAN
 */

@RestController
@RequestMapping("/api/loan-application")

public class LoanApplicationController {

    @Autowired
    private LoanApplicationService loanApplicationService;


    // Evaluate and record Loan Application
    // http:localhost:8765/api/loan-application
    @PostMapping()
    public ResponseEntity<LoanApplicationDto> evaluateAndRecordLoanApplication(@RequestBody LoanApplicationDto loanApplicationDto) {
        LoanApplicationDto loanApplicationDto1 = loanApplicationService.evaluateAndRecordLoanApplication(loanApplicationDto);
        return ResponseEntity.ok(loanApplicationDto1);
    }

    // List Loan Application Records
    // http:localhost:8765/api/loan-application/12345678912
    @GetMapping("/{identificationNumber}")
    public ResponseEntity<List<LoanApplicationDto>> findAllByIdentificationNumber(@PathVariable Long identificationNumber) {
        return ResponseEntity.ok(loanApplicationService.findAllByIdentificationNumber(identificationNumber));
    }


}
