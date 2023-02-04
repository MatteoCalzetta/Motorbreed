package com.example.motorbreedfinal.motorbreedpay;

public class MotorbreedPayController {
    public void isPaymentValid(String idAd){
        MotorbreedPayBoundary motorbreedPayBoundary = new MotorbreedPayBoundary();
        motorbreedPayBoundary.convalidPayment(idAd);
    }
}
