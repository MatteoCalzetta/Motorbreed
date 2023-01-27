package com.example.motorbreedfinal.Model.exceptions;

import java.io.Serial;

public class FailedResearchException extends Exception{
    @Serial
    private static final long serialVersionUID = 2L;
    public FailedResearchException (String message){
        super(message);
    }
}
