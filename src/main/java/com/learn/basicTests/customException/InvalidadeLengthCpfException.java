package com.learn.basicTests.customException;

public class InvalidadeLengthCpfException extends RuntimeException {

    private final String code;
    private final String message;
    
    public InvalidadeLengthCpfException (String message, String code) {
        super(String.format("[%s] - %s", code, message));

        this.code = code;
        this.message = message;
    }
    

}
