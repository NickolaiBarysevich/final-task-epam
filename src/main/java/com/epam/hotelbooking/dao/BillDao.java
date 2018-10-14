package com.epam.hotelbooking.dao;

import com.epam.hotelbooking.entity.Bill;
import com.epam.hotelbooking.exception.DaoException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BillDao extends AbstractDao<Bill> {

    private static final String SAVE_QUERY_VALUES = " VALUES(?, ?, ?, ?, ?)";
    private static final String WHERE_APPLICATION_ID = " WHERE application_id=?";

    public BillDao(Connection connection) {
        super(connection);
    }

    public Optional<Bill> findBillByApplicationId(Long applicationId) throws DaoException {
        return executeForSingleResult(SELECT_FROM_QUERY + Bill.TABLE_NAME + WHERE_APPLICATION_ID, applicationId);
    }

    public boolean removeBillByApplicationId(Long applicationId) throws DaoException {
        return update(DELETE_FROM_QUERY + getTableName() + WHERE_APPLICATION_ID, applicationId);
    }

    @Override
    protected String getTableName() {
        return Bill.TABLE_NAME;
    }

    @Override
    protected String getSaveQuery() {
        return SAVE_QUERY_VALUES;
    }

    @Override
    protected Object[] extractValuesForSaving(Bill item) {
        List<Object> values = new ArrayList<>();

        values.add(item.getId());
        values.add(item.getCost());
        values.add(item.getRoomId());
        values.add(item.getApplicationId());
        values.add(item.getClientId());

        return values.toArray();
    }
}
