package com.epam.hotelbooking.builder;

import com.epam.hotelbooking.entity.Application;
import com.epam.hotelbooking.entity.Bill;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.entity.dto.ApplicationDto;
import com.epam.hotelbooking.exception.BuilderException;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Creates an object of the {@link ApplicationDto}.
 *
 * @author Nickolai Barysevich.
 */
public class ApplicationDtoBuilder implements Builder<ApplicationDto> {

    private static final String FULL_NAME = "full_name";
    private static final String CLASS = "class";
    private static final String APPLICATION_CLIENT_ID = "application.client_id";

    /**
     * {@inheritDoc}
     *
     * @param parameters the result set from which an object must be created.
     * @return
     * @throws BuilderException
     */
    @Override
    public ApplicationDto build(ResultSet parameters) throws BuilderException {
        try {
            Long id = parameters.getLong(Application.ID);
            String login = parameters.getString(User.USERNAME);
            String fullName = parameters.getString(FULL_NAME);
            String phoneNumber = parameters.getString(User.PHONE_NUMBER);
            Integer places = parameters.getInt(Application.PLACES);
            Date checkIn = parameters.getDate(Application.CHECK_IN_DATE);
            Date checkOut = parameters.getDate(Application.CHECK_OUT_DATE);
            String roomClass = parameters.getString(CLASS);

            Long roomId = parameters.getLong(Bill.ROOM_ID);
            if (roomId == 0) {
                roomId = null;
            }

            BigDecimal cost = parameters.getBigDecimal(Bill.COST);
            Long clientId = parameters.getLong(APPLICATION_CLIENT_ID);

            return new ApplicationDto(id, login, fullName, phoneNumber, places, checkIn,
                    checkOut, roomClass, roomId, cost, clientId);
        } catch (SQLException e) {
            throw new BuilderException(e.getMessage(), e);
        }
    }
}
