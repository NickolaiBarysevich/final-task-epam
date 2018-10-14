package com.epam.hotelbooking.util;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DecimalValidatorTest {

    private static final String TEST_DECIMAL_VALUE = "43.67";
    private static final String INVALID_TEST_DECIMAL_VALUE = "43,67";

    @Test
    public void shouldReturnTrueOnValidDecimal() {
        DecimalValidator decimalValidator = new DecimalValidator();
        boolean actual = decimalValidator.validate(TEST_DECIMAL_VALUE);

        Assert.assertTrue(actual);
    }

    @Test
    public void shouldReturnFalseOnInvalidDecimal() {
        DecimalValidator decimalValidator = new DecimalValidator();
        boolean actual = decimalValidator.validate(INVALID_TEST_DECIMAL_VALUE);

        Assert.assertFalse(actual);
    }

}