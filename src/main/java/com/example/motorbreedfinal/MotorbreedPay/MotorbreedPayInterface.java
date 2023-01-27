package com.example.motorbreedfinal.MotorbreedPay;

public interface MotorbreedPayInterface {
    public void startTransaction(String firstName, String secondName, float price);

    public int convalidPayment();
}
