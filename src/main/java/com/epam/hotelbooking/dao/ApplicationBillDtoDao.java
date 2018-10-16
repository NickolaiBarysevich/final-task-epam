package com.epam.hotelbooking.dao;

import com.epam.hotelbooking.entity.Identifiable;
import com.epam.hotelbooking.entity.dto.ApplicationBillDto;
import com.epam.hotelbooking.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * Contains methods for work with view which
 * select table "application" joined by table
 * "bill"
 *
 * @author Nickolai Barysevich
 */
public class ApplicationBillDtoDao extends AbstractDao<ApplicationBillDto> {

    /**
     * Select query for that view
     */
    private static final String SELECT_QUERY = "SELECT application.id, places, check_in_date," +
            " check_out_date, room_class_id, status, bill.id AS bill_id, cost, room_id FROM application LEFT JOIN bill ON " +
            "(application_id = application.id)";

    /**
     * "WHERE" condition for the view that selects
     * only non-canceled records and sorts it by id
     * in descending order
     */
    private static final String WHERE_CLIENT_ID_CONDITION =
            " WHERE application.client_id = ? AND status !='canceled' ORDER BY application.id DESC";

    /**
     * "WHERE id=" condition for the view
     */
    private static final String WHERE_APPLICATION_ID_CONDITION =
            " WHERE application.id = ?";


    public ApplicationBillDtoDao(Connection connection) {
        super(connection);
    }

    /**
     * Finds all records from the table by client id
     * and return into into list of view object
     *
     * @param clientId client id by which records should be searched
     * @return list of {@link ApplicationBillDto}
     * @throws DaoException if some dao error has occurred
     */
    public List<ApplicationBillDto> findApplicationAndBillsByClientId(Long clientId) throws DaoException {
        return executeQuery(SELECT_QUERY + WHERE_CLIENT_ID_CONDITION, clientId);
    }

    /**
     * {@inheritDoc} {@link Dao#findById(Long)}
     *
     * @param id id of the element which should be found.
     * @return
     * @throws DaoException
     */
    @Override
    public Optional<ApplicationBillDto> findById(Long id) throws DaoException {
        return executeForSingleResult(SELECT_QUERY + WHERE_APPLICATION_ID_CONDITION, id);
    }

    /**
     * {@inheritDoc}
     * {@link Dao#findAll()}
     *
     * @return
     */
    @Override
    public List<ApplicationBillDto> findAll() {
        throw new UnsupportedOperationException();

    }

    /**
     * {@inheritDoc}
     * {@link Dao#save(Identifiable)}
     *
     * @param item object to be saved.
     * @return
     */
    @Override
    public boolean save(ApplicationBillDto item) {
        throw new UnsupportedOperationException();

    }

    /**
     * Returns table name.
     *
     * @return table name.
     */
    @Override
    protected String getTableName() {
        return ApplicationBillDto.TABLE_NAME;
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
    protected Object[] extractValuesForSaving(ApplicationBillDto item) {
        throw new UnsupportedOperationException();
    }


}
