package com.epam.hotelbooking.dao;

import com.epam.hotelbooking.entity.ApplicationStatus;
import com.epam.hotelbooking.entity.Identifiable;
import com.epam.hotelbooking.entity.dto.ApplicationDto;
import com.epam.hotelbooking.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * Works with the view of concatenation: "application",
 * "user", "bill" and "room_class". This view represents
 * information about user's application it's bill and room
 * class.
 * Can find all records that corresponds to the conditions.
 * Find records by application status. Can find record of
 * the view by application id.
 *
 * @author Nickolai Barysevich
 */
public class ApplicationDtoDao extends AbstractDao<ApplicationDto> {

    /**
     * Select query for the view
     */
    private static final String SELECT_APPLICATIONS_QUERY = "SELECT application.id, username, concat_ws(' ', name, last_name)" +
            " AS full_name, phone_number, places, check_in_date, check_out_date, class, room_id, cost," +
            " application.client_id FROM application JOIN user ON (client_id = user.id) left JOIN bill ON " +
            "(application_id = application.id) JOIN room_class ON (room_class_id = room_class.id)";

    /**
     * "WHERE status=" condition
     */
    private static final String WHERE_STATUS = " WHERE status=?";

    /**
     * "WHERE application.id=" condition
     */
    private static final String WHERE_APPLICATION_ID_CONDITION = " WHERE application.id=?";

    public ApplicationDtoDao(Connection connection) {
        super(connection);
    }

    /**
     * {@inheritDoc}
     * {@link Dao#findById(Long)}
     *
     * @param id id of the element which should be found.
     * @return
     * @throws DaoException
     */
    @Override
    public Optional<ApplicationDto> findById(Long id) throws DaoException {
        return executeForSingleResult(SELECT_APPLICATIONS_QUERY + WHERE_APPLICATION_ID_CONDITION, id);
    }

    /**
     * {@inheritDoc}
     * {@link Dao#findAll()}
     *
     * @return
     * @throws DaoException
     */
    @Override
    public List<ApplicationDto> findAll() throws DaoException {
        return executeQuery(SELECT_APPLICATIONS_QUERY);
    }

    /**
     * Finds all records of the view which status
     * is the gotten one.
     *
     * @param status status of the records that should be found
     * @return list of {@link ApplicationDto}.
     * @throws DaoException if some dao errors has occurred
     */
    public List<ApplicationDto> findAll(ApplicationStatus status) throws DaoException {
        return executeQuery(SELECT_APPLICATIONS_QUERY + WHERE_STATUS, status);
    }

    /**
     * {@inheritDoc}
     * {@link Dao#save(Identifiable)}
     *
     * @param item object to be saved.
     * @return
     */
    @Override
    public boolean save(ApplicationDto item) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns table name.
     *
     * @return table name.
     */
    @Override
    protected String getTableName() {
        return ApplicationDto.TABLE_NAME;
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
    protected Object[] extractValuesForSaving(ApplicationDto item) {
        throw new UnsupportedOperationException();
    }
}

