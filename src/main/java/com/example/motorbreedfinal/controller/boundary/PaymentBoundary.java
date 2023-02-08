package com.example.motorbreedfinal.controller.boundary;

import com.example.motorbreedfinal.controller.ResearchController;
import com.example.motorbreedfinal.motorbreedpay.MotorbreedPayBoundary;
import com.example.motorbreedfinal.view1.fagioli.AccountBean;
import com.example.motorbreedfinal.view1.fagioli.AdBean;
public class PaymentBoundary {
    public void payment(AccountBean accountBean, AdBean adbean){
        //contattiamo motorbreedpay
        MotorbreedPayBoundary motorbreedPayBoundary = new MotorbreedPayBoundary();
        motorbreedPayBoundary.startTransaction(accountBean.getFirstName(), accountBean.getLastName(),
                (Float.parseFloat(String.valueOf(adbean.getCost()/10))), adbean.getIdAd());
    }
    public void paymentIsValid(String idAd){
        ResearchController.getInstance().paymentIsValid(idAd);
    }

}
