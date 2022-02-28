package com.innova.smsservice.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * DTO model for SmsDto data.
 *
 * @author Ahmet AKAN
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmsDto {

    @NotBlank
    private String phoneNumber;

    private String message;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastname;

    @NotBlank
    private String loanApplicationDate;

    @NotNull
    private Double loanLimit;

    @NotBlank
    private String loanApplicationStatus;

}
