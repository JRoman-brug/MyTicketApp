package com.jrb.auth_service.auth.exceptions;

public class EmailAlreadyUserException extends RuntimeException {
    public EmailAlreadyUserException(String msg) {
        super(msg);
    }
}
