package com.epam.hotelbooking.dao;

import com.epam.hotelbooking.entity.ApplicationStatus;
import com.epam.hotelbooking.entity.dto.ApplicationDto;
import com.epam.hotelbooking.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ApplicationDtoDao extends AbstractDao<ApplicationDto> {

    private static final String SELECT_APPLICATIONS_QUERY = "SELECT application.id, username, concat_ws(' ', name, last_name)" +
            " AS full_name, phone_number, places, check_in_date, check_out_date, class, room_id, cost," +
            " application.client_id FROM application JOIN user ON (client_id = user.id) left JOIN bill ON " +
            "(application_id = application.id) JOIN room_class ON (room_class_id = room_class.id)";

    private static final String WHERE_STATUS = " WHERE status=?";
    private static final String WHERE_APPLICATION_ID_CONDITION = " WHERE application.id=?";

    public ApplicationDtoDao(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<ApplicationDto> findById(Long id) throws DaoException {
        return executeForSingleResult(SELECT_APPLICATIONS_QUERY + WHERE_APPLICATION_ID_CONDITION, id);
    }

    @Override
    public List<ApplicationDto> findAll() throws DaoException {
        return executeQuery(SELECT_APPLICATIONS_QUERY);
    }

    public List<ApplicationDto> findAll(ApplicationStatus status) throws DaoException {
        return executeQuery(SELECT_APPLICATIONS_QUERY + WHERE_STATUS, status);
    }

    @Override
    public boolean save(ApplicationDto item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getTableName() {
        return ApplicationDto.TABLE_NAME;
    }

    @Override
    protected String getSaveQuery() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Object[] extractValuesForSaving(ApplicationDto item) {
        throw new UnsupportedOperationException();
    }
}

