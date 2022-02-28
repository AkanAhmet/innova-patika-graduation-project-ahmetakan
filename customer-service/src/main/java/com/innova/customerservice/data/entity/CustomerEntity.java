package com.innova.customerservice.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Entity model for Customer data.
 * @author Ahmet AKAN
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2
@Entity
@Table(name = "customers")
public class CustomerEntity extends BaseEntityAudit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull(message = "Identification number is mandatory.")
    @Column(name = "identification_number",unique = true)
    private Long identificationNumber;

    @NotNull(message = "Name is mandatory.")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "LastName  is mandatory.")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Salary is mandatory.")
    @Column(name = "salary")
    private Integer salary;



    @NotNull(message = "Phone number is mandatory.")
    @Column(name = "phone_number")
    private String phoneNumber;



}
