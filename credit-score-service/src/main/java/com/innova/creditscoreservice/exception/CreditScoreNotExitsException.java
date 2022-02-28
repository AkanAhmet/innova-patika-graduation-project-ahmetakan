package com.innova.creditscoreservice.exception;

/**
 * It is thrown if the Customer Credit Score not exits
 *
 * @author Ahmet AKAN
 */
public class CreditScoreNotExitsException extends RuntimeException{


    public CreditScoreNotExitsException(String message) {
        super(message);
    }
}
