package com.innova.frontend.util;

/**
 * Regular Expressions for validation Dtos
 *
 * @author Ahmet AKAN
 */
public class StringConstants {

    // Chars and whitespace only with minimum two and maximum thirty chars long in length, checking length at html input
    public static final String NAME_REGEX = "^(?![\\s.]+$)[a-zA-ZÖÜİĞŞÇöüığçş\\s.]*$";
    public static final String SURNAME_REGEX = "^(?![\\s.]+$)[a-zA-ZÖÜİĞŞÇöüığçş\\s.]*$";
    // 10 chars long, doesnt support country codes.
    public static final String PHONE_NUMBER_REGEX = "^\\d{10}$";
}
