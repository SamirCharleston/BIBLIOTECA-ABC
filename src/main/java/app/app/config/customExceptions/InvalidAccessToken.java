package com.app.livrariaabc.config.customExceptions;

public class InvalidAccessToken extends RuntimeException{
    public InvalidAccessToken(String message){
        super(message);
    }
}
