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

/**
 * The task of the {@code ConnectionPool} is to store
 * connections and give them by need of other
 * classes.
 * ConnectionPool provides singleton pattern which
 * ensure that client class get the unique object
 * of the {@link Connection}.
 *
 * @author Nickolai Barysevich.
 */
public class ConnectionPool {

    /**
     * Holds the instance of the {@code ConnectionPool}
     */
    private static class ConnectionPoolHolder {
        private static final ConnectionPool INSTANCE = new ConnectionPool();
    }

    /**
     * The pool size
     */
    private static final int CONNECTION_LIMIT = 15;

    /**
     * Helps to limit the maximum size of the connection pool.
     */
    private final Semaphore semaphore = new Semaphore(CONNECTION_LIMIT, true);

    /**
     * Ensures that the only client getting the connection
     * at the moment
     */
    private final Lock lock = new ReentrantLock();

    /**
     * The main pool which contains available connections
     */
    private final Set<Connection> pool = new HashSet<>(CONNECTION_LIMIT);

    /**
     * Library of all connections
     */
    private final Set<Connection> poolLibrary = new HashSet<>();

    /**
     * Private modifier in case of singleton pattern.
     * Loads jdbc driver.
     */
    private ConnectionPool() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            Logger logger = Logger.getLogger(ConnectionPool.class);
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Return the instance of the {@code ConnectionPool}.
     *
     * @return the instance of the {@code ConnectionPool}.
     */
    public static ConnectionPool getInstance() {
        return ConnectionPoolHolder.INSTANCE;
    }

    /**
     * Returns the connection to client class.
     * If pool has an available connection removes it from pool
     * and returns it. In case of empty pool creates a new
     * connection and put it to the library.
     *
     * @return connection.
     * @throws ConnectionException if some connection exception has occurred.
     */
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

    /**
     * Puts connection back to the pool and releases
     * semaphore for other threads.
     *
     * @param connection the connection to be put back
     */
    public void returnConnection(Connection connection) {
        try {
            lock.lock();
            pool.add(connection);
            semaphore.release();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Iterates the pool library and closes every connection.
     *
     * @throws ConnectionException if some {@link SQLException} has occurred.
     */
    public void closeConnections() throws ConnectionException {
        try {
            for (Connection connection : poolLibrary) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage(), e);
        }
    }


}
