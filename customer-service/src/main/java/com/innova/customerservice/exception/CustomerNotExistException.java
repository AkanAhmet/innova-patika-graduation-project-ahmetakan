package com.innova.customerservice.exception;
/**
 * It is thrown if the Customer DOES NOT exists with the given criteria
 * national ID of the consumer.
 * @author Ahmet AKAN
 */
public class CustomerNotExistException extends RuntimeException {
    public CustomerNotExistException(String message) {
        super(message);
    }
}
