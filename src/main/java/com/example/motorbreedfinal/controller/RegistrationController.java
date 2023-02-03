package com.example.motorbreedfinal.controller;

import com.example.motorbreedfinal.model.dao.RegisterDao;
import com.example.motorbreedfinal.model.dao.RegisterFileSystemDao;
import com.example.motorbreedfinal.model.exceptions.FailedRegistrationException;
import com.example.motorbreedfinal.view1.fagioli.RegistrationBean;

import java.io.IOException;

public class RegistrationController {

    public void registration(RegistrationBean registrationBean) throws FailedRegistrationException{

        String os = System.getProperty("os.name");

        if(os.contains("Windows")){
            RegisterFileSystemDao registerDao = new RegisterFileSystemDao();
            registerDao.registerNewAccount(registrationBean.getFirstName(), registrationBean.getLastName(), registrationBean.getEmail(),
                    registrationBean.getPassword(), registrationBean.getRole());
        }else {
            RegisterDao registerDao = new RegisterDao();
            registerDao.registerNewAccount(registrationBean.getFirstName(), registrationBean.getLastName(), registrationBean.getEmail(),
                                        registrationBean.getPassword(), registrationBean.getRole());

        }
    }
}
