package com.epam.hotelbooking.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginValidator {

    private static final String USERNAME_EXPRESSION = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";
    private static final String PASSWORD_EXPRESSION = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";

    public boolean validateUsername(String userName) {
        return matchExpression(userName, USERNAME_EXPRESSION);
    }

    public boolean validatePassword(String password) {
        return matchExpression(password, PASSWORD_EXPRESSION);
    }

    private boolean matchExpression(String toMatch, String expression) {
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(toMatch);
        return matcher.matches();
    }

}
