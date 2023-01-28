package com.example.motorbreedfinal.model.factories;

import com.example.motorbreedfinal.model.dao.SellerDao;
import com.example.motorbreedfinal.model.dao.AccountDao;
import com.example.motorbreedfinal.model.users.Seller;

public class SellerFactory extends UserFactory {

    @Override
    public Seller createAccount() {
        return new Seller();
    }

    @Override
    public AccountDao createDAO() {
        return new SellerDao();
    }
}
