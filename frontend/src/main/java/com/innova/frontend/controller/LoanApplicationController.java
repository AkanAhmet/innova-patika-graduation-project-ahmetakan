package com.innova.frontend.controller;

import com.innova.frontend.business.dto.LoanApplicationDto;
import com.innova.frontend.business.services.LoanApplicationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.List;

/**
 * Base controller class for all loan application operations.
 *
 * @author Ahmet AKAN
 */

@Controller
@Log4j2
public class LoanApplicationController {

    @Autowired
    private LoanApplicationService loanApplicationService;


    // SAVE LOAN APPLICATION
    @PostMapping("/applications")
    public String postApplication(@Valid LoanApplicationDto loanApplicationDto, Model model) {

        LoanApplicationDto loanApplicationResponseDto;
        try {
            loanApplicationResponseDto = loanApplicationService.postApplication(loanApplicationDto);

        } catch (HttpClientErrorException e) {
            log.error("From frontend, POST request Failed to loan-application-service :\t" + e.getMessage());
            return "error/something-went-wrong";
        }

        model.addAttribute("loanApplicationDtos", loanApplicationResponseDto);

        return "application-result";
    }


    // GET LOAN APPLICATION AND ACCESS RESULTS PAGE
    @GetMapping("/applications")
    public String applicationList(Model model, @RequestParam Long identificationNumber) {
        List<LoanApplicationDto> loanApplicationDtos;
        try {
            loanApplicationDtos = loanApplicationService.getLogsByID(identificationNumber);
            model.addAttribute("identificationNumber", identificationNumber);
            model.addAttribute("loanApplicationDtos", loanApplicationDtos);
        } catch (HttpClientErrorException e) {
            log.error("From frontend, Get request Failed to loan-application-service :\t" + e.getMessage());
            /** KENDİM EKLEDİM */
            return "error/something-went-wrong";

        }

        return "application-result";
    }

    @GetMapping("/application-inquiry")
    public String inquiryForm() {
        return "application-inquiry";
    }
}
