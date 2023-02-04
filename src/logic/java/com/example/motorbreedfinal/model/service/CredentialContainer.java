package com.example.motorbreedfinal.model.service;

public class CredentialContainer {

    private CredentialContainer(){

    }

    private static final String PASS = "5headLmao";

    private static final String PASS1 = "root1234";

    public static String getPass(){
        return PASS;
    }

    public static String getPass1(){
        return PASS1;
    }
}
