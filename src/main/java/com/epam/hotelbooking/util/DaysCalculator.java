package com.epam.hotelbooking.util;

import com.epam.hotelbooking.entity.Application;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DaysCalculator {

    public long getDaysBetweenDates(Application application) {
        LocalDate checkIn = application.getCheckInDate().toLocalDate();
        LocalDate checkOut = application.getCheckOutDate().toLocalDate();
        return ChronoUnit.DAYS.between(checkIn, checkOut);
    }
}
