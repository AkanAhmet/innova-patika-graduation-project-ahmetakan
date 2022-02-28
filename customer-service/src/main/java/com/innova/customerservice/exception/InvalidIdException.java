package com.innova.customerservice.exception;

/**
 * It is thrown when the ID ends up with an odd number.
 * @author Ahmet AKAN
 */
public class InvalidIdException extends RuntimeException {
    public InvalidIdException(String message) {
        super(message);
    }
}
