package com.epam.hotelbooking.exception;

/**
 * Signals that an dao exception of some sort has occurred.
 *
 * @author Nickolai Barysevich
 */
public class DaoException extends Exception{
    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
