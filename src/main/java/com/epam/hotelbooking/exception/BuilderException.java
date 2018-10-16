package com.epam.hotelbooking.exception;

/**
 * Signals that an builder exception of some sort has occurred.
 *
 * @author Nickolai Barysevich
 */
public class BuilderException extends Exception {

    public BuilderException() {
    }

    public BuilderException(String message) {
        super(message);
    }

    public BuilderException(String message, Throwable cause) {
        super(message, cause);
    }

    public BuilderException(Throwable cause) {
        super(cause);
    }
}
