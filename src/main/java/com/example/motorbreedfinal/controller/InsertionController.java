package com.example.motorbreedfinal.controller;

import com.example.motorbreedfinal.decorations.DecorateCar;
import com.example.motorbreedfinal.evaluator.EvaluatorController;
import com.example.motorbreedfinal.model.Ad;
import com.example.motorbreedfinal.model.dao.InsertionDAO;
import com.example.motorbreedfinal.model.users.LoggedUser;
import com.example.motorbreedfinal.model.Vehicle;
import com.example.motorbreedfinal.model.exceptions.FailedAdInsertionException;
import com.example.motorbreedfinal.view1.DescriptionpageControllerG;
import com.example.motorbreedfinal.view1.fagioli.AdBean;
import com.example.motorbreedfinal.view1.fagioli.CarBean;
import com.example.motorbreedfinal.view1.fagioli.DescriptionpageBean;


import java.io.FileInputStream;
import java.sql.SQLException;

public class InsertionController{

    private DescriptionpageControllerG descriptionControlllerG;

    EvaluatorController evaluatorController = new EvaluatorController();

    private Ad ad;

    Vehicle vehicle;

    FileInputStream imageStream;

    public void insertCar(CarBean carBean) {

        ad = new Ad();

        vehicle = DecorateCar.addDecorations(carBean.getDecorationsArray(), ad.getCar());

        ad.getCar().setBrand(carBean.getCarBeanBrand());
        ad.getCar().setModel(carBean.getCarBeanModel());
        ad.getCar().setFuelType(carBean.getBeanFuelType());
        ad.getCar().setHorsepower(carBean.getBeanHorsepower());
        ad.getCar().setMileage(carBean.getCarBeanMileage());
        ad.getCar().setProductionYear(carBean.getBeanProductionYear());
        ad.getCar().setImmatricolationYear(carBean.getImmatricolationYear());
        ad.getCar().setLicencePlate(carBean.getLicencePlate());
        ad.getCar().setTransmission(carBean.getTransmission());
        ad.getCar().setCarDecorations(vehicle.getDecorations());
        ad.getCar().setInsurance(carBean.isInsurance());

        vehicle.setStatus(ad.getCar());
    }

    public int startEvaluation() {
        evaluatorController = new EvaluatorController();
        int evaluatedPrice = evaluatorController.calculatePrice(vehicle);
        evaluatorController.showResult();


        evaluatorController.setInsertionController(this); // EvaluatorController in seguito deve chiamare InsertionController

        return evaluatedPrice;
    }

    public void setDescriptionController(DescriptionpageControllerG descriptionpageControllerG) {
        this.descriptionControlllerG = descriptionpageControllerG;
        descriptionpageControllerG.setInsertionController(this);

    }

    public void setPriceOnScreen(String evaluatedPrice) {
        DescriptionpageBean descriptionpageBean =  new DescriptionpageBean();
        descriptionpageBean.setEvaluatedPrice(evaluatedPrice);
        descriptionControlllerG.setProposedPrice(descriptionpageBean);
    }

    public void insertAd(AdBean adBean) {
        ad.setSeller(LoggedUser.getInstance().getSeller());
        ad.setAdCost(adBean.getCost());
        ad.setAdDescription(adBean.getDescription());
        ad.setAdLocation(adBean.getLocation());
        ad.setInsertionDate(adBean.getInsertionDate().toString());
        ad.setNumberOfClicks(0);
        ad.setPriceCertification(adBean.isPriceCertification());
        ad.setImageStream(adBean.getImageStream());

        InsertionDAO insertionDAO = new InsertionDAO();

        try {
            ad.getCar().setIdCar(String.valueOf(insertionDAO.getCarId()));

            insertionDAO.insertCar(ad.getCar());
        }catch (FailedAdInsertionException | SQLException e){
            this.descriptionControlllerG.showFailedInsertion();
        }
        try{

            insertionDAO.insertAd(ad);

        } catch (FailedAdInsertionException e) {
            this.descriptionControlllerG.showFailedInsertion();
        }


    }
}