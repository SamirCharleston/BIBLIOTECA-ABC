package com.sismanut.sismanut.config.customExceptions;

public class AlreadyFinishedException extends RuntimeException {
    public AlreadyFinishedException(String message){
        super(message);
    }
}
