package com.epam.hotelbooking.util;

import com.epam.hotelbooking.entity.Application;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Date;

public class DaysCalculatorTest {

    private static final Application TEST_APPLICATION = new Application(null, null,
            Date.valueOf("2018-10-10"), Date.valueOf("2018-10-14"), null, null, null);

    private static final int EXPECTED_DAYS = 4;

    @Test
    public void shouldReturnExpectedDays() {
        DaysCalculator daysCalculator = new DaysCalculator();
        int actual = daysCalculator.getDaysBetweenDates(TEST_APPLICATION);

        Assert.assertEquals(actual, EXPECTED_DAYS);
    }
}