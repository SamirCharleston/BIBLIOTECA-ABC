package com.sismanut.sismanut.coreClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWrapper<T> {
    private String error;
    private String message;
    private T object;
}
