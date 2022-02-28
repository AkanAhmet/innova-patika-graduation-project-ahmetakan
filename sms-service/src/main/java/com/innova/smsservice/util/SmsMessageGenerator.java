package com.innova.smsservice.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Locale;

/**
 * Sms Messages generator util for sending nice structured sms to customers
 *
 * @author Ahmet AKAN
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmsMessageGenerator {

    private String firstName;

    private String lastname;

    private String loanApplicationDate;

    private Double loanLimit;

    private String loanApplicationStatus;

    public String generateSmsMessage() {


        String message = "Dear " + firstName + " " + lastname + "," + "You applied for a loan at "+ loanApplicationDate+"."+" Result: "+ loanApplicationStatus;
        message += ". Your loan Limit is: " + loanLimit;

        return message;
    }
}
