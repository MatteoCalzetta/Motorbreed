package com.example.motorbreedfinal.Model.factories;

import com.example.motorbreedfinal.Model.Users.Account;
import com.example.motorbreedfinal.Model.Users.Buyer;
import com.example.motorbreedfinal.Model.DAO.BuyerDao;
import com.example.motorbreedfinal.Model.DAO.AccountDao;

public class BuyerFactory extends UserFactory {
    @Override
    public Buyer createAccount() {
        return new Buyer();
    }

    @Override
    public AccountDao createDAO() {
        return new BuyerDao();
    }
}
