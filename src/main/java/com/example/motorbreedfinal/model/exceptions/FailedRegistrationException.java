package com.example.motorbreedfinal.model.exceptions;

import java.io.Serial;

public class FailedRegistrationException extends Exception{
    @Serial
    private static final long serialVersionUID = 2L;
    public FailedRegistrationException (String message){
        super(message);
    }

}
