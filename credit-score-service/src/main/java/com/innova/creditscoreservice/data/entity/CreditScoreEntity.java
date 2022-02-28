package com.innova.creditscoreservice.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 *  Entity model for Credit Score data.
 * @author Ahmet AKAN
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2
@Document(value = "credit_score")
public class CreditScoreEntity extends BaseEntityAudit implements Serializable {

    @Id
    private String id;

    @NotBlank
    private Long identificationNumber;

    private int creditScore;

}
