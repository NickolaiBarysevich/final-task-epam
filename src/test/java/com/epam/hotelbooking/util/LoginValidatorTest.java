package com.epam.hotelbooking.util;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginValidatorTest {

    private static final String TEST_USERNAME = "username";
    private static final String INVALID_TEST_USERNAME = "username@";
    private static final String TEST_PASSWORD = "Password1";
    private static final String INVALID_TEST_PASSWORD = "password";

    @Test
    public void shouldReturnTrueOnValidUsername() {
        LoginValidator loginValidator = new LoginValidator() ;
        boolean actual = loginValidator.validateUsername(TEST_USERNAME);

        Assert.assertTrue(actual);
    }

    @Test
    public void shouldReturnFalseOnInvalidUsername() {
        LoginValidator loginValidator = new LoginValidator() ;
        boolean actual = loginValidator.validateUsername(INVALID_TEST_USERNAME);

        Assert.assertFalse(actual);
    }

    @Test
    public void shouldReturnTrueOnValidPassword() {
        LoginValidator loginValidator = new LoginValidator() ;
        boolean actual = loginValidator.validatePassword(TEST_PASSWORD);

        Assert.assertTrue(actual);
    }

    @Test
    public void shouldReturnFalseOnInvalidPassword() {
        LoginValidator loginValidator = new LoginValidator() ;
        boolean actual = loginValidator.validatePassword(INVALID_TEST_PASSWORD);

        Assert.assertFalse(actual);
    }

}