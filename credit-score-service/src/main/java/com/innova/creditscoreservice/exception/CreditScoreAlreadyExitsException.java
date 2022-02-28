package com.innova.creditscoreservice.exception;

/**
 * It is thrown if the Customer Credit Score already exits
 *
 * @author Ahmet AKAN
 */
public class CreditScoreAlreadyExitsException extends RuntimeException{

    public CreditScoreAlreadyExitsException(String message) {
        super(message);
    }
}
