package com.example.motorbreedfinal.Model.Users;

import com.example.motorbreedfinal.Model.Ad;
import com.example.motorbreedfinal.Model.Rating;
import com.example.motorbreedfinal.Model.Users.Account;

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
