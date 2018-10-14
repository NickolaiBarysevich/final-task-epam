package com.epam.hotelbooking.dao.manager;

import com.epam.hotelbooking.dao.*;
import com.epam.hotelbooking.dao.connection.ConnectionPool;
import com.epam.hotelbooking.exception.ConnectionException;
import com.epam.hotelbooking.exception.DaoException;

import java.sql.Connection;

public class DaoManager implements AutoCloseable {

    private ConnectionPool connectionPool;
    private Connection connection;

    public DaoManager() throws DaoException {
        connectionPool = ConnectionPool.getInstance();
        createConnection();
    }

    public UserDao getUserDao() {
        return new UserDao(connection);
    }

    public ApplicationDao getApplicationDao() {
        return new ApplicationDao(connection);
    }

    public BillDao getBillDao() {
        return new BillDao(connection);
    }

    public RoomDao getRoomDao() {
        return new RoomDao(connection);
    }

    public ApplicationBillDtoDao getApplicationBillDtoDao() {
        return new ApplicationBillDtoDao(connection);
    }

    public ApplicationDtoDao getApplicationDtoDao() {
        return new ApplicationDtoDao(connection);
    }

    public RoomDtoDao getRoomDtoDao() {
        return new RoomDtoDao(connection);
    }

    private void createConnection() throws DaoException {
        try {
            connection = connectionPool.getConnection();
        } catch (ConnectionException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public void close() {
        if (connection != null) {
            connectionPool.returnConnection(connection);
        }
    }
}
