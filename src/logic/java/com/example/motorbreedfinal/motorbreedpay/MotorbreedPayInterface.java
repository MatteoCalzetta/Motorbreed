package com.example.motorbreedfinal.motorbreedpay;

public interface MotorbreedPayInterface {
    public void startTransaction(String firstName, String secondName, float price, String idAd);

    public void convalidPayment(String idAd);
}
