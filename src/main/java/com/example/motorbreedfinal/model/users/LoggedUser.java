package com.example.motorbreedfinal.model.users;

public class LoggedUser {

    private Account account;
    private Buyer buyer;
    private Seller seller;

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    private static LoggedUser instance = null;
    private LoggedUser() {}

    public static LoggedUser getInstance() {

        if(instance==null) {
            instance = new LoggedUser();
        }
        return instance;
    }

    @Override
    public String toString() {
        return "LoggedUser{" +
                "account=" + account +
                ", buyer=" + buyer +
                ", seller=" + seller +
                ", role='" + role + '\'' +
                '}';
    }
}
