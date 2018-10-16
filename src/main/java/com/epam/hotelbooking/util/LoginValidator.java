package com.epam.hotelbooking.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates the username and password.
 *
 * @author Nickolai Barysevich.
 */
public class LoginValidator {

    private static final String USERNAME_EXPRESSION = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";
    private static final String PASSWORD_EXPRESSION = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";

    /**
     * Defines whether the username matches username pattern.
     *
     * @param userName username to validate.
     * @return true if username is valid.
     */
    public boolean validateUsername(String userName) {
        return matchExpression(userName, USERNAME_EXPRESSION);
    }

    /**
     * Defines whether the password matches password pattern.
     *
     * @param password username to validate.
     * @return true if password is valid.
     */
    public boolean validatePassword(String password) {
        return matchExpression(password, PASSWORD_EXPRESSION);
    }

    /**
     * Defines whether the string matches the expression.
     *
     * @param toMatch string to match.
     * @param expression expression by which string will be matched.
     * @return true if string matches.
     */
    private boolean matchExpression(String toMatch, String expression) {
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(toMatch);
        return matcher.matches();
    }

}
