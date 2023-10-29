package com.learn.basicTests.customException;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomException  extends RuntimeException {

    private final String code;
    private final String message;
    
    public CustomException (String message, String code) {
        super(String.format("[%s] - %s", code, message));

        this.code = code;
        this.message = message;
    }
    
}
