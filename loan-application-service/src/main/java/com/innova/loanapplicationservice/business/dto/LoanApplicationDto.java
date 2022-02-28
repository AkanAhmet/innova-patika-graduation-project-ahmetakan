package com.innova.loanapplicationservice.business.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO model for LoanApplicationDto data.
 * @author Ahmet AKAN
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanApplicationDto {

    private String id;
    private Long identificationNumber;
    private String firstName;
    private String lastName;
    private Integer salary;
    private String phoneNumber;
    private Integer creditScore;
    private Double loanLimit;
    private String loanApplicationStatus;
    private String loanApplicationDate;
    private Boolean smsStatus;

}
