package com.example.motorbreedfinal.controller;

import com.example.motorbreedfinal.model.dao.RegisterDao;
import com.example.motorbreedfinal.model.exceptions.FailedRegistrationException;
import com.example.motorbreedfinal.view1.fagioli.RegistrationBean;

public class RegistrationController {

    RegisterDao registerDao = new RegisterDao();

    public void registration(RegistrationBean registrationBean) throws FailedRegistrationException{
        registerDao.registerNewAccount(registrationBean.getFirstName(), registrationBean.getLastName(), registrationBean.getEmail(),
                                        registrationBean.getPassword(), registrationBean.getRole());
    }

}
