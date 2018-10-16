package com.epam.hotelbooking.dao;

import com.epam.hotelbooking.entity.Room;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains methods to work with
 * "room" table and its representation
 * {@link Room}
 *
 * @author Nickolai Barysevich
 */
public class RoomDao extends AbstractDao<Room> {

    /**
     * Second part for insertion query
     */
    private static final String SAVE_QUERY_VALUES = " VALUES(?, ?, ?, ?)" +
            " ON DUPLICATE KEY UPDATE status=?";

    public RoomDao(Connection connection) {
        super(connection);
    }

    /**
     * Returns table name.
     *
     * @return table name.
     */
    @Override
    protected String getTableName() {
        return Room.TABLE_NAME;
    }

    /**
     * Returns the second part of the save query.
     *
     * @return the second part of the save query.
     */
    @Override
    protected String getSaveQuery() {
        return SAVE_QUERY_VALUES;
    }

    /**
     * Extracts item fields to the object
     * array.
     *
     * @param item item to be extracted.
     * @return object array.
     */
    @Override
    protected Object[] extractValuesForSaving(Room item) {
        List<Object> values = new ArrayList<>();

        values.add(item.getId());
        values.add(item.getBeds());
        values.add(item.getStatus());
        values.add(item.getRoomClassId());

        //for duplicate key
        values.add(item.getStatus());

        return values.toArray();
    }
}
