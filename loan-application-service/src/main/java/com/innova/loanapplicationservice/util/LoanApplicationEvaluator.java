package com.innova.loanapplicationservice.util;

import java.util.HashMap;

/**
 * This Class evaluates the loan application results with customer credit score and salary
 * @author Ahmet AKAN
 */

public class LoanApplicationEvaluator {

    public HashMap<String,Double> evaluateLoanApplication(int creditScore, int salary ) {

        final double CREDIT_LIMIT_MULTIPLIER = 4;

        HashMap<String,Double> result = new HashMap<>();

        String DENY_MESSAGE = "DENIED";
        String APPROVE_MESSAGE = "APPROVED";

        double no_limit = 0.0;
        double mid_limit = 10000.0;
        double high_limit = 20000.0;


        if(creditScore < 500) { result.put(DENY_MESSAGE,no_limit);  }

        else if(creditScore >= 500 && creditScore < 1000) {

                if(salary < 5000){  result.put(APPROVE_MESSAGE,mid_limit);  }

                if(salary >= 5000){ result.put(APPROVE_MESSAGE,high_limit);  }
        }

        else if(creditScore >= 1000) {  result.put(APPROVE_MESSAGE, salary *  CREDIT_LIMIT_MULTIPLIER); }

        else{ result.put(DENY_MESSAGE,no_limit); }

        return result;

    }
}
