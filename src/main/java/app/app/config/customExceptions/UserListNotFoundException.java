package com.sismanut.sismanut.config.customExceptions;

public class UserListNotFoundException extends RuntimeException {
    public UserListNotFoundException(String message) {
        super(message);
    }
}
