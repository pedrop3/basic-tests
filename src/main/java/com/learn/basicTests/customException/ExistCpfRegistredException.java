package com.learn.basicTests.customException;

public class ExistCpfRegistredException extends RuntimeException {

    private final String code;
    private final String message;
    
    public ExistCpfRegistredException (String message, String code) {
        super(String.format("[%s] - %s", code, message));

        this.code = code;
        this.message = message;
    }
    

}
