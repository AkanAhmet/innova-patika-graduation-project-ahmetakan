package com.innova.customerservice.data.repository;

import com.innova.customerservice.data.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Contains base CRUD operations.
 * @author Ahmet AKAN
 */

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {

    // Query Method to get customer by identification number
    Optional<CustomerEntity> findCustomerByIdentificationNumber(Long identificationNumber);

    // Query Method to delete customer by his/her identification number
    void deleteByIdentificationNumber(Long identificationNumber);

}
