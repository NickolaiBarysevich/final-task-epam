package com.epam.hotelbooking.dao.connection;

import com.epam.hotelbooking.exception.ConnectionException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final int CONNECTION_LIMIT = 15;
    private final Semaphore semaphore = new Semaphore(CONNECTION_LIMIT, true);
    private final Lock lock = new ReentrantLock();
    private final Logger logger = Logger.getLogger(ConnectionPool.class);

    private final Set<Connection> pool = new HashSet<>();
    private final Set<Connection> poolLibrary = new HashSet<>();

    private ConnectionPool() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static ConnectionPool getInstance() {

        return ConnectionPoolHolder.INSTANCE;
    }

    public Connection getConnection() throws ConnectionException {
        try {
            semaphore.acquire();
            lock.lock();
            Connection connection;

            if (pool.isEmpty()) {
                connection = ConnectionCreator.getConnection();
                poolLibrary.add(connection);
            } else {
                connection = pool.stream().findAny().get();
                pool.remove(connection);
            }
            return connection;
        } catch (InterruptedException e) {
            throw new ConnectionException(e.getMessage(), e.getCause());
        } finally {
            lock.unlock();
        }
    }

    public void returnConnection(Connection connection) {
        try {
            lock.lock();
            pool.add(connection);
            semaphore.release();
        } finally {
            lock.unlock();
        }
    }

    public void closeConnections() throws ConnectionException {
        try {
            for (Connection connection : poolLibrary) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage(), e);
        }
    }

    private static class ConnectionPoolHolder {
        private static final ConnectionPool INSTANCE = new ConnectionPool();
    }

}
