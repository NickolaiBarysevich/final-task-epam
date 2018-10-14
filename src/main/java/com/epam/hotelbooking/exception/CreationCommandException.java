package com.epam.hotelbooking.exception;

public class CreationCommandException extends Exception {
    public CreationCommandException() {
    }

    public CreationCommandException(String message) {
        super(message);
    }

    public CreationCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreationCommandException(Throwable cause) {
        super(cause);
    }
}
