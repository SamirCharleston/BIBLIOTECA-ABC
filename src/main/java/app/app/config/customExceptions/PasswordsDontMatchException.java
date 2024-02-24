package com.sismanut.sismanut.config.customExceptions;

public class PasswordsDontMatchException extends RuntimeException {
    public PasswordsDontMatchException(String message) {
        super(message);
    }
}
