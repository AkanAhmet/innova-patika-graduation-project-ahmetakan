package com.innova.frontend.business.dto;

import com.innova.frontend.util.Messages;
import com.innova.frontend.util.StringConstants;
import lombok.*;

import javax.validation.constraints.*;

/**
 * DTO model for CustomerDto data.
 * @author Ahmet AKAN
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

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
    @Digits(integer = 8, fraction = 0, message = Messages.SALARY_LENGTH_ERROR)

    @NotNull
    private Integer salary;
    @NotNull
    @Pattern(regexp = StringConstants.PHONE_NUMBER_REGEX,message = Messages.NOT_A_VALID_PHONE)
    private String phoneNumber;
}
