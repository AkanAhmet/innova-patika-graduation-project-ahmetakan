package com.innova.customerservice.exception;

/**
 * It is thrown if the Customer exists with the given criteria
 * national ID of the consumer.
 * @author Ahmet AKAN
 */
public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
