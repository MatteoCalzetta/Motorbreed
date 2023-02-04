package com.example.motorbreedfinal.model.users;

import com.example.motorbreedfinal.model.Ad;

import java.util.List;

public class Buyer extends Account {
    private List<Ad> favourites;
    private List<Ad> orders;
    public void addToFavourites(Ad ad) {
        favourites.add(ad);
    }
    public void addToOrders(Ad ad) {
        orders.add(ad);
        notifyObservers();
    }

    public List<Ad> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<Ad> favourites) {
        this.favourites = favourites;
    }

    public List<Ad> getOrders() {
        return orders;
    }

    public void setOrders(List<Ad> orders) {
        this.orders = orders;
    }
}
