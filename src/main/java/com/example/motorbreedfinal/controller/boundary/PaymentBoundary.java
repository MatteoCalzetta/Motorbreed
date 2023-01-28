package com.example.motorbreedfinal.controller.boundary;

import com.example.motorbreedfinal.controller.ResearchController;
import com.example.motorbreedfinal.model.Ad;
import com.example.motorbreedfinal.motorbreedpay.MotorbreedPayBoundary;
import com.example.motorbreedfinal.view1.fagioli.AccountBean;
import com.example.motorbreedfinal.view1.fagioli.AdBean;
public class PaymentBoundary {

    private String idAd = "";
    public void payment(AccountBean accountBean, AdBean adbean){
        //contattiamo motorbreedpay
        System.out.println("l'idAd nel payment è "+adbean.getIdAd());
        MotorbreedPayBoundary motorbreedPayBoundary = new MotorbreedPayBoundary();
        motorbreedPayBoundary.startTransaction(accountBean.getFirstName(), accountBean.getLastName(),
                (Float.parseFloat(String.valueOf(adbean.getCost()/10))), adbean.getIdAd());
    }


    public void paymentIsValid(String idAd){
        System.out.println("l'idAd nella payment boundary è "+ idAd);
        ResearchController.getInstance().paymentIsValid(idAd);
    }



}
