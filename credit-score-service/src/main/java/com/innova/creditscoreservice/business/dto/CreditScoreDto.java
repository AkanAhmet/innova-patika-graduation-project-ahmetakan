package com.innova.creditscoreservice.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO model for CreditScoreDto data.
 * @author Ahmet AKAN
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditScoreDto {

    private String id;
    private long identificationNumber;
    private int creditScore;

}
