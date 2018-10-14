package com.epam.hotelbooking.dao;

import com.epam.hotelbooking.entity.dto.ApplicationBillDto;
import com.epam.hotelbooking.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ApplicationBillDtoDao extends AbstractDao<ApplicationBillDto> {

    private static final String SELECT_QUERY = "SELECT application.id, places, check_in_date," +
            " check_out_date, room_class_id, status, bill.id AS bill_id, cost, room_id FROM application LEFT JOIN bill ON " +
            "(application_id = application.id)";

    private static final String WHERE_CLIENT_ID_CONDITION =
            " WHERE application.client_id = ? AND status !='canceled' ORDER BY application.id DESC";
    private static final String WHERE_APPLICATION_ID_CONDITION =
            " WHERE application.id = ?";


    public ApplicationBillDtoDao(Connection connection) {
        super(connection);
    }

    public List<ApplicationBillDto> findApplicationAndBillsByClientId(Long id) throws DaoException {
        return executeQuery(SELECT_QUERY + WHERE_CLIENT_ID_CONDITION, id);
    }

    @Override
    public Optional<ApplicationBillDto> findById(Long id) throws DaoException {
        return executeForSingleResult(SELECT_QUERY + WHERE_APPLICATION_ID_CONDITION, id);
    }

    @Override
    public List<ApplicationBillDto> findAll() {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean save(ApplicationBillDto item) {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean removeById(Long id) {
        throw new UnsupportedOperationException();

    }

    @Override
    protected String getTableName() {
        return ApplicationBillDto.TABLE_NAME;
    }

    @Override
    protected String getSaveQuery() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Object[] extractValuesForSaving(ApplicationBillDto item) {
        throw new UnsupportedOperationException();
    }


}
