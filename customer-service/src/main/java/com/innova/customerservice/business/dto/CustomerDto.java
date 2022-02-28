package com.innova.customerservice.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO model for CustomerDto data.
 * @author Ahmet AKAN
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

    private Long id;
    private Long identificationNumber;
    private String firstName;
    private String lastName;
    private Integer salary;
    private String phoneNumber;
}
