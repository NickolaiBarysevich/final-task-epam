package com.epam.hotelbooking.util;

import com.epam.hotelbooking.entity.Application;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DaysCalculator {

    public int getDaysBetweenDates(Application application) {
        LocalDate checkIn = application.getCheckInDate().toLocalDate();
        LocalDate checkOut = application.getCheckOutDate().toLocalDate();
        return (int) ChronoUnit.DAYS.between(checkIn, checkOut);
    }
}
