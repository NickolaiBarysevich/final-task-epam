package com.epam.hotelbooking.exception;

/**
 * Signals that an service exception of some sort has occurred.
 *
 * @author Nickolai Barysevich
 */
public class ServiceException extends Exception {

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }


}
