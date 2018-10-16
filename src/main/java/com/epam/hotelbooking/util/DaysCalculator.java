package com.epam.hotelbooking.util;

import com.epam.hotelbooking.entity.Application;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Calculate days between two dates.
 *
 * @author Nickolai Barysevich.
 */
public class DaysCalculator {

    /**
     * Takes application as parameter
     * and then calculate days between
     * check in and check out dates.
     *
     * @param application application which dates must be calculated.
     * @return days between check in and check out dates.
     */
    public int calculateDaysBetweenDates(Application application) {
        LocalDate checkIn = application.getCheckInDate().toLocalDate();
        LocalDate checkOut = application.getCheckOutDate().toLocalDate();
        return (int) ChronoUnit.DAYS.between(checkIn, checkOut);
    }
}
