package com.example.motorbreedfinal.controller;

import com.example.motorbreedfinal.model.dao.CustomizeProfileDAO;
import com.example.motorbreedfinal.model.users.LoggedUser;
import com.example.motorbreedfinal.model.exceptions.FailedProfileCustomizationException;
import com.example.motorbreedfinal.view1.fagioli.AccountBean;

import java.sql.SQLException;

public class CustomizeProfileController {

    public void changeFirstName(AccountBean accountBean) {
        CustomizeProfileDAO customizeProfileDAO = new CustomizeProfileDAO();
        try {
            customizeProfileDAO.changeFirstname(accountBean.getFirstName(), LoggedUser.getInstance().getAccount().getEmail());
        } catch (SQLException e) {
            //to-do
        }
        LoggedUser.getInstance().getAccount().changeCredentials("firstname", accountBean.getFirstName());
    }

    public void changeLastName(AccountBean accountBean){
        CustomizeProfileDAO customizeProfileDAO = new CustomizeProfileDAO();
        try {
            customizeProfileDAO.changeLastname(accountBean.getLastName(), LoggedUser.getInstance().getAccount().getEmail());
        } catch (SQLException e) {
            //to-do
        }
        LoggedUser.getInstance().getAccount().changeCredentials("lastname", accountBean.getLastName());
    }

    public void changeEmail(AccountBean accountBean) throws FailedProfileCustomizationException{
        CustomizeProfileDAO customizeProfileDAO = new CustomizeProfileDAO();
        customizeProfileDAO.changeEmail(accountBean.getEmail(), LoggedUser.getInstance().getAccount().getEmail());
        LoggedUser.getInstance().getAccount().changeCredentials("email", accountBean.getEmail());
    }


}
