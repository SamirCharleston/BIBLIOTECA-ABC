package com.sismanut.sismanut.config.customExceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AlreadyRegisteredAddressException extends RuntimeException{
    public AlreadyRegisteredAddressException(String message){
        super(message);
    }
}
