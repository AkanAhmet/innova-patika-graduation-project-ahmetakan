package com.innova.customerservice.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Audit Entity model for Customer data.
 * @author Ahmet AKAN
 */

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
// Fields that do not want to appear in the JSON data are specified.
@JsonIgnoreProperties(value = {"created_date,update_date"},allowGetters = true)
public abstract class BaseEntityAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,updatable = false)
    private Long id;

    @Column(name = "created_by",nullable = false)
    @CreatedBy
    private String createdBy;

    @Column(name = "created_date",nullable = false,updatable = false)
    @CreatedDate
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "update_by",nullable = false)
    @LastModifiedBy
    private String updateBy;

    @Column(name = "update_date",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updateDate;
}
