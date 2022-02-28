package com.innova.loanapplicationservice.data.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;


import javax.persistence.*;
import java.util.Date;

/**
 * Audit Entity model for Loan Application data.
 * @author Ahmet AKAN
 */

public abstract class BaseEntityAudit {

    @Id
    private Long id;

    @CreatedBy
    private String createdBy;


    @CreatedDate
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;


    @LastModifiedBy
    private String updateBy;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updateDate;
}