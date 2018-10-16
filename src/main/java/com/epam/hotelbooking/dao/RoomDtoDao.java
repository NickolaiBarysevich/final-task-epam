package com.epam.hotelbooking.dao;

import com.epam.hotelbooking.entity.Identifiable;
import com.epam.hotelbooking.entity.dto.RoomDto;
import com.epam.hotelbooking.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * Contains methods to work with view
 * of "room" which joined by "room_class" and
 * its representation as {@link RoomDto}.
 *
 * @author Nickolai Barysevich
 */
public class RoomDtoDao extends AbstractDao<RoomDto> {

    /**
     * Select query for that view
     */
    private static final String SELECT_ROOMS_QUERY = "SELECT room.id, beds, class, price " +
            "FROM room JOIN room_class " +
            "ON (room_class_id = room_class.id)";

    /**
     * "WHERE" condition for the view that selects
     * only "free" records and sorts it by id
     * in ascending order
     */
    private static final String WHERE_FREE = " WHERE status = 'free' ORDER BY room.id ASC";

    /**
     * "WHERE" condition for the view that selects
     * only "non-free" records and sorts it by id
     * in ascending order
     */
    private static final String WHERE_BUSY = " WHERE status != 'free' ORDER BY room.id ASC";

    public RoomDtoDao(Connection connection) {
        super(connection);
    }

    /**
     * {@inheritDoc} {@link Dao#findById(Long)}
     *
     * @param id id of the element which should be found.
     * @return
     * @throws DaoException
     */
    @Override
    public Optional<RoomDto> findById(Long id) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     * {@link Dao#findAll()}
     *
     * @return
     */
    @Override
    public List<RoomDto> findAll() throws DaoException {
        return executeQuery(SELECT_ROOMS_QUERY);
    }

    /**
     * Return list of free of non-free records.
     *
     * @param free the parameter by which records will be chosen
     * @return list of "free" {@link RoomDto} if {@code free == true},
     * otherwise return list of non-free records.
     * @throws DaoException if some dao error has occurred.
     */
    public List<RoomDto> findAll(boolean free) throws DaoException {
        String query = SELECT_ROOMS_QUERY + (free ? WHERE_FREE : WHERE_BUSY);
        return executeQuery(query);
    }

    /**
     * {@inheritDoc}
     * {@link Dao#save(Identifiable)}
     *
     * @param item object to be saved.
     * @return
     */
    @Override
    public boolean save(RoomDto item) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns table name.
     *
     * @return table name.
     */
    @Override
    protected String getTableName() {
        return RoomDto.TABLE_NAME;
    }

    /**
     * {@inheritDoc}
     * {@link AbstractDao#getSaveQuery()}
     *
     * @return
     */
    @Override
    protected String getSaveQuery() {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     * {@link AbstractDao#extractValuesForSaving(Identifiable)} ()}
     *
     * @return
     */
    @Override
    protected Object[] extractValuesForSaving(RoomDto item) {
        throw new UnsupportedOperationException();
    }
}
