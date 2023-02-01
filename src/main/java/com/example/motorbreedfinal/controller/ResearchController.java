package com.example.motorbreedfinal.controller;

import com.example.motorbreedfinal.controller.boundary.EmailBoundary;
import com.example.motorbreedfinal.controller.boundary.PaymentBoundary;
import com.example.motorbreedfinal.model.Ad;
import com.example.motorbreedfinal.model.dao.ResearchDAO;
import com.example.motorbreedfinal.model.users.LoggedUser;
import com.example.motorbreedfinal.model.exceptions.FailedResearchException;
import com.example.motorbreedfinal.view1.fagioli.*;
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
        ads = researchDAO.findAdsByAdvancedFilter(advancedResearchBean.getBrand(), advancedResearchBean.getModel(), advancedResearchBean.getFuelType(),
                advancedResearchBean.getProductionYear(), advancedResearchBean.getStartingHP(), advancedResearchBean.getMaxHP(),
                advancedResearchBean.getTransmission(), advancedResearchBean.getStartingPrice(), advancedResearchBean.getMaxPrice(),
                advancedResearchBean.getStartingMileage(), advancedResearchBean.getMaxMileage(), advancedResearchBean.getDecorations());

        AdBean adBean = new AdBean();
        adBean.setAds(ads);
        return adBean;
    }
    public void addFavorites(FavouritesBean favouritesBean) {
        ResearchDAO researchDAO = new ResearchDAO();
        researchDAO.addFavourites(favouritesBean.getIdAd(),favouritesBean.getIdBuyer());
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
        Ad ad = researchDAO.findAdById(idAd, LoggedUser.getInstance().getBuyer().getIdAccount());
        LoggedUser.getInstance().getBuyer().addToOrders(ad);
        System.out.println(LoggedUser.getInstance().getBuyer().getOrders());
    }

    public List<Ad> getBuyerOrders() {
        List <Ad> ads = researchDAO.findBuyerOrders(LoggedUser.getInstance().getBuyer().getIdAccount());
        return ads;
    }
}
