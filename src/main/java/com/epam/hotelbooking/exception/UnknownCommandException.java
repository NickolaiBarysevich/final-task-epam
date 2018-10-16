package com.epam.hotelbooking.exception;

/**
 * Signals that command that is tried to process
 * is unknown.
 *
 * @author Nickolai Barysevich
 */
public class UnknownCommandException extends RuntimeException {

    public UnknownCommandException() {
    }

    public UnknownCommandException(String message) {
        super(message);
    }

    public UnknownCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownCommandException(Throwable cause) {
        super(cause);
    }
}
