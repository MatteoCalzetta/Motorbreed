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
import com.example.motorbreedfinal.view1.FxmlLoader;

import java.io.InputStream;
import java.sql.SQLException;

public class InsertionController extends DecorateCar {

    private DescriptionpageControllerG descriptionControlllerG;

    private DescriptionpageBean descriptionpageBean;

    private Ad ad;

    Vehicle vehicle;

    InputStream imageStream;

    public void insertCar(CarBean carBean) {

        ad = new Ad();

        vehicle = DecorateCar.addDecorations(carBean.getDecorationsArray(), ad.getCar());

        ad.getCar().setBrand(carBean.getBrand());
        ad.getCar().setModel(carBean.getModel());
        ad.getCar().setFuelType(carBean.getFuelType());
        ad.getCar().setHorsepower(carBean.getHorsepower());
        ad.getCar().setMileage(carBean.getMileage());
        ad.getCar().setProductionYear(carBean.getProductionYear());
        ad.getCar().setImmatricolationYear(carBean.getImmatricolationYear());
        ad.getCar().setLicencePlate(carBean.getLicencePlate());
        ad.getCar().setTransmission(carBean.getTransmission());
        ad.getCar().setCarDecorations(vehicle.getDecorations());
        ad.getCar().setInsurance(carBean.isInsurance());

        vehicle.setStatus(ad.getCar());

    }

    public void startEvaluation() {
        EvaluatorController evaluatorController = new EvaluatorController();
        evaluatorController.calculatePrice(vehicle);

        evaluatorController.setInsertionController(this); // EvaluatorController in seguito deve chiamare InsertionController
    }

    public void setDescriptionController(DescriptionpageControllerG descriptionpageControllerG) {
        this.descriptionControlllerG = descriptionpageControllerG;
        descriptionpageControllerG.setInsertionController(this);

    }

    public void setPriceOnScreen(String evaluatedPrice) {
        descriptionpageBean = new DescriptionpageBean();
        descriptionpageBean.setEvaluatedPrice(evaluatedPrice);
        descriptionControlllerG.setProposedPrice(descriptionpageBean);
    }

    public void insertAd(AdBean adBean) {
        ad.setSeller(LoggedUser.getInstance().getSeller());
        ad.setCost(adBean.getCost());
        ad.setDescription(adBean.getDescription());
        ad.setLocation(adBean.getLocation());
        ad.setInsertionDate(adBean.getInsertionDate().toString());
        ad.setNumberOfClicks(0);
        ad.setPriceCertification(adBean.isPriceCertification());
        this.imageStream = adBean.getImageStream();

        InsertionDAO insertionDAO = new InsertionDAO();

        try {
            ad.getCar().setIdCar(String.valueOf(insertionDAO.getCarId()));
            insertionDAO.insertCar(ad.getCar());
        }catch (FailedAdInsertionException | SQLException e){
            this.descriptionControlllerG.showFailedInsertion();
            System.out.println(e.getMessage());

        }
        try{
            insertionDAO.insertAd(ad, ad.getSeller().getIdAccount(), imageStream);
        } catch (FailedAdInsertionException e) {
            this.descriptionControlllerG.showFailedInsertion();
            System.out.println(e.getMessage());
        }

        FxmlLoader.setPage("SellerHomepage");
    }
}