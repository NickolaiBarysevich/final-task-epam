package com.epam.hotelbooking.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecimalValidator {

    private static final String VALIDATE_EXPRESSION = "\\d{1,6}(\\.\\d{1,2})?";

    public boolean validate(String walletValue) {
        Pattern pattern = Pattern.compile(VALIDATE_EXPRESSION);
        Matcher matcher = pattern.matcher(walletValue);
        return matcher.matches();
    }
}
