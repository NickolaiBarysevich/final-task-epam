package com.epam.hotelbooking.builder;

import com.epam.hotelbooking.entity.Bill;
import com.epam.hotelbooking.exception.BuilderException;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Creates an object of the {@link Bill}.
 */
public class BillBuilder implements Builder<Bill> {

    /**
     * {@inheritDoc}
     *
     * @param parameters the result set from which an object must be created.
     * @return
     * @throws BuilderException
     */
    @Override
    public Bill build(ResultSet parameters) throws BuilderException {
        try {
            long id = parameters.getLong(Bill.ID);
            BigDecimal cost = parameters.getBigDecimal(Bill.COST);
            Long roomId = parameters.getLong(Bill.ROOM_ID);
            Long applicationId = parameters.getLong(Bill.APPLICATION_ID);
            Long clientId = parameters.getLong(Bill.CLIENT_ID);

            return new Bill(id, cost, roomId, applicationId, clientId);
        } catch (SQLException e) {
            throw new BuilderException(e.getMessage(), e.getCause());
        }
    }
}
