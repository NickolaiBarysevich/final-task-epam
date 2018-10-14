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

public abstract class AbstractDao<T extends Identifiable> implements Dao<T> {

    protected static final String SELECT_FROM_QUERY = "SELECT * FROM ";
    protected static final String WHERE_ID_CONDITION = " WHERE id=?";
    protected static final String DELETE_FROM_QUERY = "DELETE FROM ";

    private static final String INSERT_INTO_QUERY = "INSERT INTO ";

    private final Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<T> findById(Long id) throws DaoException {
        String query = SELECT_FROM_QUERY + getTableName() + WHERE_ID_CONDITION;

        return executeForSingleResult(query, id);
    }

    protected Optional<T> executeForSingleResult(String query, Object... parameters) throws DaoException {
        List<T> queryResult = executeQuery(query, parameters);
        return queryResult.size() == 1
                ? Optional.of(queryResult.get(0))
                : Optional.empty();
    }

    @Override
    public List<T> findAll() throws DaoException {
        String query = SELECT_FROM_QUERY + getTableName();
        return executeQuery(query);
    }

    @Override
    public boolean save(T item) throws DaoException {
        String query = INSERT_INTO_QUERY + getTableName() + getSaveQuery();

        return update(query, extractValuesForSaving(item));
    }

    @Override
    public boolean removeById(Long id) throws DaoException {
        String query = DELETE_FROM_QUERY + getTableName() + WHERE_ID_CONDITION;
        return update(query, id);
    }

    protected boolean update(String query, Object... parameters) throws DaoException {
        int result = executeUpdate(query, parameters);
        return result != 0;
    }


    private int executeUpdate(String query, Object... parameters) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            prepareStatement(statement, parameters);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

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

    protected abstract String getTableName();

    protected abstract String getSaveQuery();

    protected abstract Object[] extractValuesForSaving(T item);


}
