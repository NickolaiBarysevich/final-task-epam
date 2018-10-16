package com.epam.hotelbooking.util;

import java.math.BigDecimal;

/**
 * Calculates the cost of the application.
 *
 * @author Nickolai Barysevich
 */
public class CostCalculator {

    /**
     * Multiply the room price, days and guests amount.
     *
     * @param roomPrice price of the room.
     * @param days days amount.
     * @param guests guests amount.
     * @return cost of the application.
     */
    public BigDecimal calculateCost(BigDecimal roomPrice, long days, int guests) {
        BigDecimal daysDecimal = new BigDecimal(days);
        BigDecimal placesDecimal = new BigDecimal(guests);

        roomPrice = roomPrice.multiply(placesDecimal);
        roomPrice = roomPrice.multiply(daysDecimal);
        return roomPrice;
    }
}
