package com.example.motorbreedfinal.Controller;

import com.example.motorbreedfinal.Model.DAO.LoginDao;
import com.example.motorbreedfinal.Model.DAO.AccountDao;
import com.example.motorbreedfinal.Model.DAO.ResearchDAO;
import com.example.motorbreedfinal.Model.Users.Account;
import com.example.motorbreedfinal.Model.Users.Buyer;
import com.example.motorbreedfinal.Model.Users.LoggedUser;
import com.example.motorbreedfinal.Model.Users.Seller;
import com.example.motorbreedfinal.Model.factories.UserFactory;
import com.example.motorbreedfinal.view1.Fagioli.AccountHomepageBean;
import com.example.motorbreedfinal.view1.Fagioli.LoginBean;

import javax.security.auth.login.FailedLoginException;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    private Account account;
    private Seller seller;
    private Buyer buyer;
    public AccountHomepageBean Login(LoginBean loginBean) throws FailedLoginException, SQLException, IOException {

        LoginDao loginDao = new LoginDao(); // creazione loginDao per trovare role

        ResearchDAO researchDAO = new ResearchDAO();

        String role = loginDao.checkCredentials(loginBean.getEmail(), loginBean.getPassword());

        UserFactory myFactory;

        // sezione di codice polimorfo per istanziare oggetti di tipo buyer/seller e i rispettivi Dao

        if (role.equals("Seller")) {
            myFactory = UserFactory.getFactory(UserFactory.ROLE_SELLER);
            seller = (Seller) myFactory.createAccount();
            LoggedUser.getInstance().setAccount(seller);
            LoggedUser.getInstance().setRole(role);
            AccountDao accountDao = myFactory.createDAO();
            accountDao.setAccount(seller, loginBean.getEmail());
            LoggedUser.getInstance().setSeller(seller);
            account = seller;

        } else {
            myFactory = UserFactory.getFactory(UserFactory.ROLE_BUYER);
            buyer = (Buyer) myFactory.createAccount();
            LoggedUser.getInstance().setAccount(buyer);
            LoggedUser.getInstance().setRole(role);
            AccountDao accountDao = myFactory.createDAO();
            accountDao.setAccount(buyer, loginBean.getEmail());
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

    public void buyerSignIn(){

    }

    public void sellerSignIn() {

    }

    public void facebookLogin(){

    }

    public void googleLogin() {

    }

    public Account getAccount() {
        return this.account;
    }
}
