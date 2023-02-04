package com.example.motorbreedfinal.model.factories;

import com.example.motorbreedfinal.model.users.Buyer;
import com.example.motorbreedfinal.model.dao.BuyerDao;
import com.example.motorbreedfinal.model.dao.AccountDao;

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
