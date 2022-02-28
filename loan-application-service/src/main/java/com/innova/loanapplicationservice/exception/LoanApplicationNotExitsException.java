package com.innova.loanapplicationservice.exception;

/**
 * It is thrown if the Customer Loan Application history doesn't exists
 * @author Ahmet AKAN
 */

public class LoanApplicationNotExitsException extends RuntimeException{

    public LoanApplicationNotExitsException(String message) {super(message);}
}
