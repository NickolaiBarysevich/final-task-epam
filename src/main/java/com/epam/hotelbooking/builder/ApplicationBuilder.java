package com.epam.hotelbooking.builder;

import com.epam.hotelbooking.entity.Application;
import com.epam.hotelbooking.entity.ApplicationStatus;
import com.epam.hotelbooking.exception.BuilderException;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Creates an object of the {@link Application}.
 */
public class ApplicationBuilder implements Builder<Application> {

    /**
     * {@inheritDoc}
     *
     * @param parameters the result set from which an object must be created.
     * @return
     * @throws BuilderException
     */
    @Override
    public Application build(ResultSet parameters) throws BuilderException {
        try {
            Long id = parameters.getLong(Application.ID);
            Integer places = parameters.getInt(Application.PLACES);
            Date checkIn = parameters.getDate(Application.CHECK_IN_DATE);
            Date checkOut = parameters.getDate(Application.CHECK_OUT_DATE);
            String statusValue = parameters.getString(Application.STATUS).toUpperCase();
            ApplicationStatus applicationStatus = ApplicationStatus.valueOf(statusValue);
            Long clientId = parameters.getLong(Application.CLIENT_ID);
            Long roomClassId = parameters.getLong(Application.ROOM_CLASS_ID);

            return new Application(id, places, checkIn, checkOut, applicationStatus, clientId, roomClassId);
        } catch (SQLException e) {
            throw new BuilderException(e.getMessage(), e.getCause());
        }
    }
}
