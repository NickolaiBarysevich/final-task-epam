package com.epam.hotelbooking.dao;

import com.epam.hotelbooking.entity.Bill;
import com.epam.hotelbooking.exception.DaoException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Contains methods to work with
 * "bill" table and its representation
 * {@link Bill}
 *
 * @author Nickolai Barysevich
 */
public class BillDao extends AbstractDao<Bill> {

    /**
     * Second part of the save query
     */
    private static final String SAVE_QUERY_VALUES = " VALUES(?, ?, ?, ?, ?)";

    /**
     * "WHERE application_id=" for the select query
     */
    private static final String WHERE_APPLICATION_ID = " WHERE application_id=?";

    /**
     * Delete query
     */
    protected static final String DELETE_FROM_QUERY = "DELETE FROM ";

    public BillDao(Connection connection) {
        super(connection);
    }

    /**
     * Finds record in the table which
     * application id corresponds to the
     * gotten one.
     *
     * @param applicationId application id by which record should be found.
     * @return optional of the bill
     * @throws DaoException if some dao error has occurred.
     */
    public Optional<Bill> findBillByApplicationId(Long applicationId) throws DaoException {
        return executeForSingleResult(SELECT_FROM_QUERY + Bill.TABLE_NAME + WHERE_APPLICATION_ID, applicationId);
    }

    /**
     * Delete bill from the table by application id.
     *
     * @param applicationId application id by which bill must be deleted.
     * @return true if query executed successfully.
     * @throws DaoException if some dao error has occurred.
     */
    public boolean removeBillByApplicationId(Long applicationId) throws DaoException {
        return executeUpdate(DELETE_FROM_QUERY + getTableName() + WHERE_APPLICATION_ID, applicationId) != 0;
    }

    /**
     * Return table name.
     *
     * @return table name.
     */
    @Override
    protected String getTableName() {
        return Bill.TABLE_NAME;
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
