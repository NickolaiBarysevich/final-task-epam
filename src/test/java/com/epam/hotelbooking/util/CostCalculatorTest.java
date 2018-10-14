package com.epam.hotelbooking.util;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class CostCalculatorTest {

    private static final BigDecimal TEST_ROOM_PRICE = new BigDecimal("24.99");
    private static final int TEST_DAYS = 7;
    private static final int TEST_GUESTS = 2;

    private static final BigDecimal EXPECTED_COST = new BigDecimal("349.86");

    @Test
    public void shouldReturnExpectedValue() {
        CostCalculator costCalculator = new CostCalculator();
        BigDecimal actual = costCalculator.calculateCost(TEST_ROOM_PRICE, TEST_DAYS, TEST_GUESTS);

        Assert.assertEquals(actual, EXPECTED_COST);
    }
}