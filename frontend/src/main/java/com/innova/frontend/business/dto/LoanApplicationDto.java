package com.innova.frontend.business.dto;

import com.innova.frontend.util.Messages;
import com.innova.frontend.util.StringConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * DTO model for LoanApplicationDto data.
 *
 * @author Ahmet AKAN
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplicationDto {

    @NotNull
    @Digits(integer = 11, fraction = 0, message = Messages.NOT_A_VALID_ID_ERROR)
    @Min(value = 10000000000L, message = Messages.NOT_A_VALID_ID_ERROR)
    private Long identificationNumber;
    @NotBlank
    @Pattern(regexp = StringConstants.NAME_REGEX, message = Messages.NON_ALPHABETICAL_ERROR)
    private String firstName;
    @NotBlank
    @Pattern(regexp = StringConstants.SURNAME_REGEX, message = Messages.NON_ALPHABETICAL_ERROR)
    private String lastName;
    @NotBlank
    @Pattern(regexp = StringConstants.PHONE_NUMBER_REGEX, message = Messages.NOT_A_VALID_PHONE)
    private String phoneNumber;
    @NotNull
    private Integer salary;

    /**
     * This fields doesn't take @NotNull annotation because this fields required for response
     */

    private Integer loanLimit;
    private Integer creditScore;
    private String loanApplicationStatus;
    private String loanApplicationDate;
    private Boolean smsStatus;
}
