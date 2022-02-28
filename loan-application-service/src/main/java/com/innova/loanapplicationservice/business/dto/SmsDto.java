package com.innova.loanapplicationservice.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

/**
 * DTO model for SmsDto data.
 * @author Ahmet AKAN
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmsDto {

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastname;

    @NotBlank
    private String loanApplicationDate;

    @NotBlank
    private Double loanLimit;

    @NotBlank
    private String loanApplicationStatus;

}
