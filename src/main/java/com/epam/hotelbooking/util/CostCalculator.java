package com.epam.hotelbooking.util;

import java.math.BigDecimal;

public class CostCalculator {

    public BigDecimal calculateCost(BigDecimal roomPrice, long days, int guests) {
        BigDecimal daysDecimal = new BigDecimal(days);
        BigDecimal placesDecimal = new BigDecimal(guests);

        roomPrice = roomPrice.multiply(placesDecimal);
        roomPrice = roomPrice.multiply(daysDecimal);
        return roomPrice;
    }
}
