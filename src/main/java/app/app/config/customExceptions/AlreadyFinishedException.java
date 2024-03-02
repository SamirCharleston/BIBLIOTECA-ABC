package com.app.livrariaabc.config.customExceptions;

public class AlreadyFinishedException extends RuntimeException {
    public AlreadyFinishedException(String message){
        super(message);
    }
}
