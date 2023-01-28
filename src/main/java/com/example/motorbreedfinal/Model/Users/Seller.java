package com.example.motorbreedfinal.model.users;

import com.example.motorbreedfinal.model.Ad;
import com.example.motorbreedfinal.model.Rating;

import java.util.List;

public class Seller extends Account {
    private List<Ad> adList;
    private List<Rating> ratingList;

    public boolean addAdvertisement(Ad ad) {
        return false;
    }

    public boolean addRating(Rating rating) {
        return false;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "adList=" + adList +
                ", ratingList=" + ratingList +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", idAccount='" + idAccount + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
