package com.epam.hotelbooking.builder;

import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.RoomStatus;
import com.epam.hotelbooking.exception.BuilderException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Creates an object of the {@link Room}.
 *
 * @author Nickolai Barysevich.
 */
public class RoomBuilder implements Builder<Room> {

    /**
     * {@inheritDoc}
     *
     * @param parameters the result set from which an object must be created.
     * @return
     * @throws BuilderException
     */
    @Override
    public Room build(ResultSet parameters) throws BuilderException {
        try {
            Long id = parameters.getLong(Room.ID);
            Integer beds = parameters.getInt(Room.BEDS);
            String statusParam = parameters.getString(Room.STATUS);
            RoomStatus status = RoomStatus.valueOf(statusParam.toUpperCase());
            Long roomClassId = parameters.getLong(Room.ROOM_CLASS_ID);

            return new Room(id, beds, status, roomClassId);
        } catch (SQLException e) {
            throw new BuilderException(e.getMessage(), e.getCause());
        }
    }
}
