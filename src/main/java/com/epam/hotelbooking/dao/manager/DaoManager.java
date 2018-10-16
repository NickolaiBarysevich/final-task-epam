package com.epam.hotelbooking.dao.manager;

import com.epam.hotelbooking.dao.*;
import com.epam.hotelbooking.dao.connection.ConnectionPool;
import com.epam.hotelbooking.exception.ConnectionException;
import com.epam.hotelbooking.exception.DaoException;

import java.sql.Connection;

/**
 * Invokes the {@link ConnectionPool} and creates connection.
 * Then creates and return dao class for client class.
 *
 * @author Nickolai Barysevich.
 */
public class DaoManager implements AutoCloseable {

    private ConnectionPool connectionPool;
    private Connection connection;

    /**
     * Creates the connection and constructs this {@code DaoManager}.
     *
     * @throws DaoException if some dao error has occurred.
     */
    public DaoManager() throws DaoException {
        connectionPool = ConnectionPool.getInstance();
        createConnection();
    }

    /**
     * Returns {@link UserDao}.
     *
     * @return {@link UserDao}.
     */
    public UserDao getUserDao() {
        return new UserDao(connection);
    }

    /**
     * Returns {@link ApplicationDao}.
     *
     * @return {@link ApplicationDao}.
     */
    public ApplicationDao getApplicationDao() {
        return new ApplicationDao(connection);
    }

    /**
     * Returns {@link BillDao}.
     *
     * @return {@link BillDao}.
     */
    public BillDao getBillDao() {
        return new BillDao(connection);
    }

    /**
     * Returns {@link RoomDao}.
     *
     * @return {@link RoomDao}.
     */
    public RoomDao getRoomDao() {
        return new RoomDao(connection);
    }

    /**
     * Returns {@link ApplicationBillDtoDao}.
     *
     * @return {@link ApplicationBillDtoDao}.
     */
    public ApplicationBillDtoDao getApplicationBillDtoDao() {
        return new ApplicationBillDtoDao(connection);
    }

    /**
     * Returns {@link ApplicationDtoDao}.
     *
     * @return {@link ApplicationDtoDao}.
     */
    public ApplicationDtoDao getApplicationDtoDao() {
        return new ApplicationDtoDao(connection);
    }

    /**
     * Returns {@link RoomDtoDao}.
     *
     * @return {@link RoomDtoDao}.
     */
    public RoomDtoDao getRoomDtoDao() {
        return new RoomDtoDao(connection);
    }

    /**
     * Asks connection from {@link ConnectionPool}.
     *
     * @throws DaoException if some dao error has occurred.
     */
    private void createConnection() throws DaoException {
        try {
            connection = connectionPool.getConnection();
        } catch (ConnectionException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    /**
     * Return connection to connection pool.
     */
    @Override
    public void close() {
        if (connection != null) {
            connectionPool.returnConnection(connection);
        }
    }
}
