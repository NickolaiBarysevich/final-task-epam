package com.epam.hotelbooking.dao;

import com.epam.hotelbooking.entity.Room;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class RoomDao extends AbstractDao<Room> {

    private static final String SAVE_QUERY_VALUES = " VALUES(?, ?, ?, ?)" +
            " ON DUPLICATE KEY UPDATE status=?";

    public RoomDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return Room.TABLE_NAME;
    }

    @Override
    protected String getSaveQuery() {
        return SAVE_QUERY_VALUES;
    }

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
