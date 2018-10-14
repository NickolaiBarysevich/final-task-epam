package com.epam.hotelbooking.dao;

import com.epam.hotelbooking.entity.Application;
import com.epam.hotelbooking.entity.ApplicationStatus;
import com.epam.hotelbooking.exception.DaoException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ApplicationDao extends AbstractDao<Application> {

    private static final String SAVE_QUERY_VALUES = " VALUES(?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE status=?";

    private static final String UPDATE_STATUS = "UPDATE application SET status=? where id=?";
    private static final String FIND_CANCELED_APPLICATION_QUERY = SELECT_FROM_QUERY + Application.TABLE_NAME + WHERE_ID_CONDITION + " AND status='canceled'";

    public ApplicationDao(Connection connection) {
        super(connection);
    }

    public boolean markApplication(Long id, ApplicationStatus status) throws DaoException {
        return update(UPDATE_STATUS, status, id);
    }

    public Optional<Application> findCanceledApplicationById(Long id) throws DaoException {
        return executeForSingleResult(FIND_CANCELED_APPLICATION_QUERY, id);
    }

    @Override
    protected String getSaveQuery() {
        return SAVE_QUERY_VALUES;
    }

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

    @Override
    protected String getTableName() {
        return Application.TABLE_NAME;
    }

}
