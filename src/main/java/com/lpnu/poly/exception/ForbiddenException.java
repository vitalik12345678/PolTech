package com.lpnu.poly.exception;

public class ForbiddenException extends IllegalStateException{

    private static final long serialVersionUID = 525L;
    private static final String FORBIDDEN_EXCEPTION = "you don't have access for this content";

    public ForbiddenException(String message) {
        super((message == null || message.isEmpty()) ? FORBIDDEN_EXCEPTION : message);
    }

    public ForbiddenException() {
        super(FORBIDDEN_EXCEPTION);
    }

}
