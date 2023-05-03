package com.somosf5community.exception;

public class ProfileNotFoundException  extends RuntimeException {
    public ProfileNotFoundException(String message) {
        super(message);
    }
}
