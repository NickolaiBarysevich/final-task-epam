package com.epam.hotelbooking.exception;

/**
 * Signals that an connection exception of some sort has occurred.
 *
 * @author Nickolai Barysevich
 */
public class ConnectionException extends Exception {
    public ConnectionException() {
    }

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionException(Throwable cause) {
        super(cause);
    }
}
