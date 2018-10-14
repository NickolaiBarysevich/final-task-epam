package com.epam.hotelbooking.util;

import java.math.BigDecimal;

public class CostCalculator {

    public BigDecimal calculateCost(BigDecimal roomPrice, long days, int places) {
        BigDecimal daysDecimal = new BigDecimal(days);
        BigDecimal placesDecimal = new BigDecimal(places);

        roomPrice = roomPrice.multiply(placesDecimal);
        roomPrice = roomPrice.multiply(daysDecimal);
        return roomPrice;
    }
}
