package com.learn.basicTests.customException;

public class PersonNotFoundException extends RuntimeException {

    private final String code;
    private final String message;
    
    public PersonNotFoundException (String message, String code) {
        super(String.format("[%s] - %s", code, message));

        this.code = code;
        this.message = message;
    }
    

}
