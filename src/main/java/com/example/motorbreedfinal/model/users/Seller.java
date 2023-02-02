package com.example.motorbreedfinal.model.users;

import com.example.motorbreedfinal.model.Ad;

import java.util.List;

public class Seller extends Account {
    private List<Ad> adList;

    public List<Ad> getAdList() {
        return adList;
    }

    public void setAdList(List<Ad> adList) {
        this.adList = adList;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "adList=" + adList +
                ", ratingList=" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", idAccount='" + idAccount + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
