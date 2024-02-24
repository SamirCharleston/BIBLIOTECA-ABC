package com.sismanut.sismanut.config.customExceptions;

public class OldPasswordProvidedException extends RuntimeException {
    public OldPasswordProvidedException(String message){
        super(message);
    }
}
