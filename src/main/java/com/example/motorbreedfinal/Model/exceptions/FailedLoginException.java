package com.example.motorbreedfinal.model.exceptions;

import java.io.Serial;

public class FailedLoginException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;

    public FailedLoginException (String message){
        super(message);
    }

    public FailedLoginException (Throwable cause) {
        super(cause);
    }

    public FailedLoginException (String message, Throwable cause) {
        super(" +++ " + message + " +++ ", cause);
    }
}
