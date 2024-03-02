package com.app.livrariaabc.config.customExceptions;

public class PasswordNotValidException extends RuntimeException{
    public PasswordNotValidException(String message){
        super(message);
    }
}
