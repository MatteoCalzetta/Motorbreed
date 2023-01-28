package com.example.motorbreedfinal.motorbreedpay;

public interface MotorbreedPayInterface {
    public void startTransaction(String firstName, String secondName, float price);

    public int convalidPayment();
}
