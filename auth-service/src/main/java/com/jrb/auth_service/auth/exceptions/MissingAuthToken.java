package com.jrb.auth_service.auth.exceptions;

public class MissingAuthToken extends RuntimeException {
    public MissingAuthToken(String message) {
        super(message);
    }

}
