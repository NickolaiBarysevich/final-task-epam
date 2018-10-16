package com.epam.hotelbooking.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Defines whether the string matches decimal pattern.
 *
 * @author Nickola Barysevich.
 */
public class DecimalValidator {

    private static final String VALIDATE_EXPRESSION = "\\d{1,6}(\\.\\d{1,2})?";

    /**
     * Validate the gotten string.
     *
     * @param value string to validate.
     * @return true if string matches decimal pattern.
     */
    public boolean validate(String value) {
        Pattern pattern = Pattern.compile(VALIDATE_EXPRESSION);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
