package com.lpnu.poly.exception;

public class ExistsException extends IllegalStateException{

    private static final long serialVersionUID = 55525L;
    private static final String EXISTS_EXCEPTION = "Object exists";

    public ExistsException(String message) {
        super((message == null || message.isEmpty()) ? EXISTS_EXCEPTION : message);
    }

    public ExistsException() {
        super(EXISTS_EXCEPTION);
    }

}
