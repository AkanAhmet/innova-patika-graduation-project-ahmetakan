package com.innova.loanapplicationservice.data.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 *  Entity model for Loan application data.
 * @author Ahmet AKAN
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2
@Document(value = "loan_application_records")
public class LoanApplicationEntity extends BaseEntityAudit implements Serializable {

    @Id
    private String id;

    @NotBlank

    private Long identificationNumber;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private Double salary;

    @NotBlank
    private String phoneNumber;

    @NotNull
    private Integer creditScore;

    @NotNull
    private Double loanLimit;

    @NotBlank
    private String loanApplicationStatus;

    @CreatedDate
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private String loanApplicationDate;

    @NotBlank
    private Boolean smsStatus;





}
