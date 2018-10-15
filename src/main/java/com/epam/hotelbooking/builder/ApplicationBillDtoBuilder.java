package com.epam.hotelbooking.builder;

import com.epam.hotelbooking.entity.Application;
import com.epam.hotelbooking.entity.ApplicationStatus;
import com.epam.hotelbooking.entity.Bill;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.dto.ApplicationBillDto;
import com.epam.hotelbooking.exception.BuilderException;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Creates an object of the {@link ApplicationBillDto}.
 *
 * @author Nickolai Barysevich.
 */
public class ApplicationBillDtoBuilder implements Builder<ApplicationBillDto> {

    private static final String BILL_ID = "bill_id";

    /**
     * {@inheritDoc}
     *
     * @param parameters the result set from which an object must be created.
     * @return
     * @throws BuilderException
     */
    @Override
    public ApplicationBillDto build(ResultSet parameters) throws BuilderException {
        try {
            Long applicationId = parameters.getLong(Application.ID);
            Integer places = parameters.getInt(Application.PLACES);
            Date checkIn = parameters.getDate(Application.CHECK_IN_DATE);
            Date checkOut = parameters.getDate(Application.CHECK_OUT_DATE);
            String statusValue = parameters.getString(Application.STATUS);
            String statusValueUpperCase = statusValue.toUpperCase();
            ApplicationStatus applicationStatus = ApplicationStatus.valueOf(statusValueUpperCase);
            Long roomClassId = parameters.getLong(Room.ROOM_CLASS_ID);
            Long billId = parameters.getLong(BILL_ID);
            BigDecimal cost = parameters.getBigDecimal(Bill.COST);
            Long roomId = parameters.getLong(Bill.ROOM_ID);

            return new ApplicationBillDto(applicationId, places, checkIn, checkOut, applicationStatus, roomClassId, billId, cost, roomId);
        } catch (SQLException e) {
            throw new BuilderException(e.getMessage(), e);
        }
    }
}
