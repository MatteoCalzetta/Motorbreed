package com.example.motorbreedfinal.controller;

import com.example.motorbreedfinal.controller.boundary.EmailBoundary;
import com.example.motorbreedfinal.controller.boundary.PaymentBoundary;
import com.example.motorbreedfinal.model.Ad;
import com.example.motorbreedfinal.model.Car;
import com.example.motorbreedfinal.model.dao.ResearchDAO;
import com.example.motorbreedfinal.model.users.LoggedUser;
import com.example.motorbreedfinal.model.exceptions.FailedResearchException;
import com.example.motorbreedfinal.view1.fagioli.*;

import java.sql.SQLException;
import java.util.List;

public class ResearchController {

    private static ResearchController instance = null;

    public static ResearchController getInstance() {
        if(instance == null){
            instance = new ResearchController();
        }
        return instance;
    }
    private ResearchController(){}

    ResearchDAO researchDAO = new ResearchDAO();
    public AdBean baseResearch(ResearchBean researchBean) throws FailedResearchException {
        List<Ad> ads = null;
        ads = researchDAO.findAdsByBaseFilter(researchBean.getBrand(), researchBean.getStartingPrice(), researchBean.getMaxPrice(),
                researchBean.getStartingMileage(), researchBean.getMaxMileage());

        AdBean adBean = new AdBean();
        adBean.setAds(ads);
        return adBean;
    }

    public AdBean advancedResearch(AdvancedResearchBean advancedResearchBean) throws FailedResearchException {
        List<Ad> ads = null;

        Car car = new Car();

        car.setBrand(advancedResearchBean.getBrand());
        car.setModel(advancedResearchBean.getModel());
        car.setFuelType(advancedResearchBean.getFuelType());
        car.setProductionYear(advancedResearchBean.getProductionYear());
        car.setHorsepower(Integer.parseInt(advancedResearchBean.getStartingHP()));
        car.setTransmission(advancedResearchBean.getTransmission());
        car.setMileage(Integer.parseInt(advancedResearchBean.getStartingMileage()));
        car.setDecorations(advancedResearchBean.getDecorations());

        ads = researchDAO.findAdsByAdvancedFilter(car, advancedResearchBean.getMaxHP(),
                advancedResearchBean.getStartingPrice(), advancedResearchBean.getMaxPrice(),
                advancedResearchBean.getMaxMileage());

        AdBean adBean = new AdBean();
        adBean.setAds(ads);
        return adBean;
    }
    public void addFavorites(FavouritesBean favouritesBean, AdBean adBean) {
        try {
            researchDAO.addFavourites(favouritesBean.getIdAd(),favouritesBean.getIdBuyer());
        } catch (SQLException e) {
            //unhandled
        }
        LoggedUser.getInstance().getBuyer().addToFavourites((Ad) adBean.getAds());
    }

    public void sendEmail(EmailBean emailBean){
        EmailBoundary emailBoundary = new EmailBoundary();
        emailBoundary.sendEmail(emailBean.getFromEmail(), emailBean.getPassword(), emailBean.getToEmail(), emailBean.getDescription());
    }

    public void payment(AccountBean accountBean, AdBean adBean){
        PaymentBoundary paymentBoundary = new PaymentBoundary();
        paymentBoundary.payment(accountBean, adBean);
    }

    public void paymentIsValid(String idAd) {
        Ad ad = null;
        try {
            ad = researchDAO.findAdById(idAd, LoggedUser.getInstance().getBuyer().getIdAccount());
        } catch (SQLException e) {
            //unhandled
        }
        LoggedUser.getInstance().getBuyer().addToOrders(ad);
    }
    public List<Ad> getBuyerOrders() {
        return researchDAO.findBuyerOrders(LoggedUser.getInstance().getBuyer().getIdAccount());
    }
}
