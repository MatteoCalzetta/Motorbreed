package com.example.motorbreedfinal.controller;

import com.example.motorbreedfinal.model.dao.*;
import com.example.motorbreedfinal.model.users.Account;
import com.example.motorbreedfinal.model.users.Buyer;
import com.example.motorbreedfinal.model.users.LoggedUser;
import com.example.motorbreedfinal.model.users.Seller;
import com.example.motorbreedfinal.model.factories.UserFactory;
import com.example.motorbreedfinal.view1.fagioli.AccountHomepageBean;
import com.example.motorbreedfinal.view1.fagioli.LoginBean;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;

public class LoginController {
    public AccountHomepageBean login(LoginBean loginBean) throws FailedLoginException, SQLException {

        Account account;

        Seller seller;

        Buyer buyer;

        ResearchDAO researchDAO = new ResearchDAO();

        String os = System.getProperty("os.name");

        String role;

        final String SO = "Windows";

        if(os.contains(SO)){

            LoginFileSystemDao loginDao = new LoginFileSystemDao();

            role = loginDao.checkCredentials(loginBean.getEmail(), loginBean.getPassword());

        }else {

            LoginDao loginDao = new LoginDao(); // creazione loginDao per trovare role

             role = loginDao.checkCredentials(loginBean.getEmail(), loginBean.getPassword());

        }


        UserFactory myFactory;

        // sezione di codice polimorfo per istanziare oggetti di tipo buyer/seller e i rispettivi Dao

        if (role.equals("Seller")) {
            myFactory = UserFactory.getFactory(UserFactory.ROLE_SELLER);
            seller = (Seller) myFactory.createAccount();
            LoggedUser.getInstance().setAccount(seller);
            LoggedUser.getInstance().setRole(role);

            if(os.contains(SO)){
                AccountFileSystemDao accountFileSystemDao = new AccountFileSystemDao();
                accountFileSystemDao.setAccount(seller, loginBean.getEmail());
            }else {
                AccountDao accountDao = myFactory.createDAO();
                accountDao.setAccount(seller, loginBean.getEmail());
            }

            LoggedUser.getInstance().setSeller(seller);
            account = seller;

        } else {
            myFactory = UserFactory.getFactory(UserFactory.ROLE_BUYER);
            buyer = (Buyer) myFactory.createAccount();
            LoggedUser.getInstance().setAccount(buyer);
            LoggedUser.getInstance().setRole(role);

            if(os.contains("SO")){
                AccountFileSystemDao accountFileSystemDao = new AccountFileSystemDao();
                accountFileSystemDao.setAccount(buyer, loginBean.getEmail());
            }else {
                AccountDao accountDao = myFactory.createDAO();
                accountDao.setAccount(buyer, loginBean.getEmail());
            }


            LoggedUser.getInstance().setBuyer(buyer);
            buyer.setFavourites(researchDAO.findFavoriteAds(LoggedUser.getInstance().getAccount().getIdAccount()));
            buyer.setOrders(researchDAO.findBuyerOrders(LoggedUser.getInstance().getAccount().getIdAccount()));
            account = buyer;
        }

        AccountHomepageBean accountHomepageBean = new AccountHomepageBean();
        accountHomepageBean.setFirstName(account.getFirstName());
        accountHomepageBean.setLastName(account.getLastName());
        accountHomepageBean.setRole(role);

        return accountHomepageBean;
    }
}
