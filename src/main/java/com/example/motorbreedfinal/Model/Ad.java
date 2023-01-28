package com.example.motorbreedfinal.model;

import com.example.motorbreedfinal.model.users.Seller;
import javafx.scene.image.Image;


public class Ad {
    private String idAd;
    private int cost;
    private String description;
    private String location;
    private String insertionDate;
    private int numberOfClicks;
    private Car car;
    private Seller seller;
    private Image image;
    private boolean priceCertification;


    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isPriceCertification() {
        return priceCertification;
    }

    public String getIdAd() {
        return idAd;
    }

    public void setIdAd(String idAd) {
        this.idAd = idAd;
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

    public String getInsertionDate() {
        return insertionDate;
    }

    public void setInsertionDate(String insertionDate) {
        this.insertionDate = insertionDate;
    }

    public int getNumberOfClicks() {
        return numberOfClicks;
    }

    public void setNumberOfClicks(int numberOfClicks) {
        this.numberOfClicks = numberOfClicks;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public boolean isPriceCertificated() {
        return priceCertification;
    }

    public void setPriceCertification(boolean priceCertification) {
        this.priceCertification = priceCertification;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Ad(){
        this.car = new Car();
    }

    @Override
    public String toString() {
        return "Ad{" +
                "idAd='" + idAd + '\'' +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", insertionDate='" + insertionDate + '\'' +
                ", numberOfClicks=" + numberOfClicks +
                ", car=" + car +
                ", seller=" + seller +
                '}';
    }
}
