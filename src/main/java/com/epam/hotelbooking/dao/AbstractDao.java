package com.epam.hotelbooking.dao;

import com.epam.hotelbooking.builder.Builder;
import com.epam.hotelbooking.builder.factory.BuilderFactory;
import com.epam.hotelbooking.entity.Identifiable;
import com.epam.hotelbooking.exception.BuilderException;
import com.epam.hotelbooking.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Contains basic methods for work with db.
 *
 * @param <T> type of object that represents concrete table.
 */
public abstract class AbstractDao<T extends Identifiable> implements Dao<T> {

    /**
     * Query for getting records from table
     */
    protected static final String SELECT_FROM_QUERY = "SELECT * FROM ";

    /**
     * Condition for the select query to find records with
     * concrete id
     */
    protected static final String WHERE_ID_CONDITION = " WHERE id=?";

    /**
     * Part of insertion into table query
     */
    private static final String INSERT_INTO_QUERY = "INSERT INTO ";

    /**
     * Object to connect to database
     */
    private final Connection connection;

    /**
     * Constructs this object.
     *
     * @param connection object which provides connection to database
     */
    public AbstractDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * {@inheritDoc} {@link Dao#findById(Long)}
     *
     * @param id id of the element which should be found.
     * @return
     * @throws DaoException
     */
    @Override
    public Optional<T> findById(Long id) throws DaoException {
        String query = SELECT_FROM_QUERY + getTableName() + WHERE_ID_CONDITION;

        return executeForSingleResult(query, id);
    }

    /**
     * Return the first record of the view.
     *
     * @param query      query to be executed.
     * @param parameters parameters be which query should be prepared.
     * @return optional of the first record if if exist
     * otherwise return {@code Optional.empty}
     * @throws DaoException if some dao errors has occurred.
     */
    protected Optional<T> executeForSingleResult(String query, Object... parameters) throws DaoException {
        List<T> queryResult = executeQuery(query, parameters);
        return queryResult.size() == 1
                ? Optional.of(queryResult.get(0))
                : Optional.empty();
    }

    /**
     * {@inheritDoc} {@link Dao#findAll()}
     *
     * @return
     * @throws DaoException
     */
    @Override
    public List<T> findAll() throws DaoException {
        String query = SELECT_FROM_QUERY + getTableName();
        return executeQuery(query);
    }

    /**
     * {@inheritDoc} {@link Dao#save(Identifiable)}
     *
     * @param item object to be saved.
     * @return
     * @throws DaoException
     */
    @Override
    public boolean save(T item) throws DaoException {
        String query = INSERT_INTO_QUERY + getTableName() + getSaveQuery();

        return executeUpdate(query, extractValuesForSaving(item)) != 0;
    }

    /**
     * Provides update queries.
     * Return true if query was executed successfully.
     *
     * @param query      query to be executed.
     * @param parameters parameters be which query should be prepared.
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     * or (2) 0 for SQL statements that return nothing
     * @throws DaoException if some dao errors has occurred.
     */
    protected int executeUpdate(String query, Object... parameters) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            prepareStatement(statement, parameters);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    /**
     * Provides "view" queries.
     * Uses {@link Builder} for parse record into object.
     *
     * @param query      query to be executed.
     * @param parameters parameters be which query should be prepared.
     * @return list of records represents as {@link T}
     * @throws DaoException if some dao errors has occurred.
     */
    @SuppressWarnings("unchecked")
    protected List<T> executeQuery(String query, Object... parameters) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            Builder<? extends Identifiable> builder = BuilderFactory.create(getTableName());
            prepareStatement(statement, parameters);
            ResultSet resultSet = statement.executeQuery();
            List<T> result = new ArrayList<>();
            while (resultSet.next()) {
                T item = (T) builder.build(resultSet);
                result.add(item);
            }
            return result;
        } catch (SQLException | BuilderException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    /**
     * Prepares statement.
     * Takes an array of objects as parameters
     * that sets to the statement.
     *
     * @param statement  statement ot be prepared.
     * @param parameters parameters to be set.
     * @throws SQLException if some sql errors has occurred.
     */
    private void prepareStatement(PreparedStatement statement, Object[] parameters) throws SQLException {
        if (parameters.length == 0) {
            return;
        }

        for (int i = 0; i < parameters.length; i++) {
            Object parameter = parameters[i];
            if (parameter != null) {
                statement.setString(i + 1, parameter.toString());
            } else {
                statement.setString(i + 1, null);

            }
        }
    }

    /**
     * Returns table name.
     *
     * @return table name.
     */
    protected abstract String getTableName();

    /**
     * Return last part of insertion query.
     *
     * @return last part of insertion query.
     */
    protected abstract String getSaveQuery();

    /**
     * Extracts item fields into array of objects.
     *
     * @param item item to be extracted.
     * @return array of fields.
     */
    protected abstract Object[] extractValuesForSaving(T item);

}
