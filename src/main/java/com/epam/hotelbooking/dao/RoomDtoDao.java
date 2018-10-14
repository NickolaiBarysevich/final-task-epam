package com.epam.hotelbooking.dao;

import com.epam.hotelbooking.entity.dto.RoomDto;
import com.epam.hotelbooking.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class RoomDtoDao extends AbstractDao<RoomDto> {

    private static final String SELECT_ROOMS_QUERY = "SELECT room.id, beds, class, price " +
            "FROM room JOIN room_class " +
            "ON (room_class_id = room_class.id)";
    private static final String WHERE_FREE = " WHERE status = 'free' ORDER BY room.id ASC";
    private static final String WHERE_BUSY = " WHERE status != 'free' ORDER BY room.id ASC";

    public RoomDtoDao(Connection connection) {
        super(connection);
    }


    @Override
    public Optional<RoomDto> findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<RoomDto> findAll() throws DaoException {
        return executeQuery(SELECT_ROOMS_QUERY);
    }

    public List<RoomDto> findAll(boolean free) throws DaoException {
        String query = SELECT_ROOMS_QUERY + (free ? WHERE_FREE : WHERE_BUSY);
        return executeQuery(query);
    }

    @Override
    public boolean save(RoomDto item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getTableName() {
        return RoomDto.TABLE_NAME;
    }

    @Override
    protected String getSaveQuery() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Object[] extractValuesForSaving(RoomDto item) {
        throw new UnsupportedOperationException();
    }
}
