package com.epam.hotelbooking.builder;

import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.dto.RoomDto;
import com.epam.hotelbooking.exception.BuilderException;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Creates an object of the {@link RoomDto}.
 */
public class RoomDtoBuilder implements Builder<RoomDto> {

    private static final String CLASS = "class";
    private static final String PRICE = "price";

    /**
     * {@inheritDoc}
     *
     * @param parameters the result set from which an object must be created.
     * @return
     * @throws BuilderException
     */
    @Override
    public RoomDto build(ResultSet parameters) throws BuilderException {
        try {
            Long roomId = parameters.getLong(Room.ID);
            Integer beds = parameters.getInt(Room.BEDS);
            String roomClass = parameters.getString(CLASS);
            BigDecimal cost = parameters.getBigDecimal(PRICE);

            return new RoomDto(roomId, beds, roomClass, cost);
        } catch (SQLException e) {
            throw new BuilderException(e.getMessage(), e);
        }
    }
}
