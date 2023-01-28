package com.example.motorbreedfinal.view1.fagioli;

import com.example.motorbreedfinal.model.Ad;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class AdBean {

    private String idAd;
    private int cost;
    private String description;
    private String location;
    private Date insertionDate;
    private boolean priceCertification;

    private InputStream imageStream;

    List<Ad> ads;

    public AdBean() {
    }

    public AdBean(Date date, String description, String adLocation, int cost, boolean priceCertification, InputStream imageStream) {
        this.insertionDate = date;
        this.description = description;
        this.location = adLocation;
        this.cost = cost;
        this.priceCertification = priceCertification;
        this.imageStream = imageStream;

    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getInsertionDate() {
        return insertionDate;
    }

    public void setInsertionDate(Date insertionDate) {
        this.insertionDate = insertionDate;
    }

    public String getIdAd() {
        return idAd;
    }

    public void setIdAd(String idAd) {
        this.idAd = idAd;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }

    public boolean isPriceCertification() {
        return priceCertification;
    }

    public InputStream getImageStream() {
        return imageStream;
    }

    public boolean Validation() {
        if(cost > 0 && imageStream != null){
            return true;
        }
        return false;
    }
}