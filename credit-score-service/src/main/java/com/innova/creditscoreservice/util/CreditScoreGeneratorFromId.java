package com.innova.creditscoreservice.util;


public class CreditScoreGeneratorFromId {

    /**
     * Utility method to calculate credit score by the given
     * national id's last number. We are assuming that the national id
     * cannot end with a odd number.
     *
     * @param identificationNumber of the customer
     * @return 200 if id ends with "2",
     * 400 if id ends with "4",
     * 600 if id ends with "6",
     * 800 if id ends with "8" and 1000 if the id ends with "0".
     */
    public int generateNewCreditScore(Long identificationNumber) {

            int creditScore;
            if (String.valueOf(identificationNumber).endsWith("2")) {
                creditScore = 200;
            } else if (String.valueOf(identificationNumber).endsWith("4")) {
                creditScore = 400;
            } else if (String.valueOf(identificationNumber).endsWith("6")) {
                creditScore = 600;
            } else if (String.valueOf(identificationNumber).endsWith("8")) {
                creditScore = 800;
            } else if (String.valueOf(identificationNumber).endsWith("0")) {
                creditScore = 1000;
            } else {
                creditScore = 0;
            }
            return creditScore;

    }
}
