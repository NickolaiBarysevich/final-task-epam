package com.epam.hotelbooking.dao;

import com.epam.hotelbooking.entity.Application;
import com.epam.hotelbooking.entity.ApplicationStatus;
import com.epam.hotelbooking.exception.DaoException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Works with table "application" and it's object
 * representation {@link Application}.
 *
 * @author Nickolai Barysevich
 */
public class ApplicationDao extends AbstractDao<Application> {

    /**
     * Second part for insertion query
     */
    private static final String SAVE_QUERY_VALUES = " VALUES(?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE status=?";

    /**
     * Update query for table "application". Updates
     * field "status"
     */
    private static final String UPDATE_STATUS = "UPDATE application SET status=? where id=?";

    public ApplicationDao(Connection connection) {
        super(connection);
    }

    /**
     * Changes application status to the specified.
     *
     * @param id id of the application which status must be changed
     * @param status status that must be set
     * @return true if query executed successfully.
     * @throws DaoException if some dao error has occurred
     */
    public boolean markApplication(Long id, ApplicationStatus status) throws DaoException {
        return executeUpdate(UPDATE_STATUS, status, id) != 0;
    }

    /**
     * Returns table name.
     *
     * @return table name.
     */
    @Override
    protected String getTableName() {
        return Application.TABLE_NAME;
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
    protected Object[] extractValuesForSaving(Application item) {
        List<Object> values = new ArrayList<>();

        values.add(item.getId());
        values.add(item.getPlaces());
        values.add(item.getCheckInDate());
        values.add(item.getCheckOutDate());
        values.add(item.getApplicationStatus());
        values.add(item.getClientId());
        values.add(item.getRoomClassId());

        //for duplicate key
        values.add(item.getApplicationStatus());

        return values.toArray();
    }

}
