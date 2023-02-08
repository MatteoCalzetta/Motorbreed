package com.example.motorbreedfinal.model.factories;

import com.example.motorbreedfinal.model.users.Account;
import com.example.motorbreedfinal.model.dao.AccountDao;

public abstract class UserFactory {

    public static final String ROLE_SELLER = "Seller";

    public static final String ROLE_BUYER = "Buyer";

    public static UserFactory getFactory(String s) {
        if(s.equals(ROLE_SELLER)){
            return new SellerFactory();

        }else {
            return new BuyerFactory();
        }
    }

    public abstract Account createAccount();

    public abstract AccountDao createDAO();
}
