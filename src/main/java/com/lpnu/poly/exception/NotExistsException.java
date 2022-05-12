package com.lpnu.poly.exception;

public class NotExistsException extends IllegalStateException {

    private static final String MESSAGE = "Not exists";

    public NotExistsException() {
        super(MESSAGE);
    }

    public NotExistsException(String message) {
        super(message.isEmpty() ? MESSAGE : message);
    }
}
