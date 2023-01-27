package com.example.motorbreedfinal.Controller;

import com.example.motorbreedfinal.Model.DAO.RegisterDao;
import com.example.motorbreedfinal.Model.exceptions.FailedRegistrationException;
import com.example.motorbreedfinal.view1.Fagioli.RegistrationBean;

public class RegistrationController {

    RegisterDao registerDao = new RegisterDao();

    public void Registration(RegistrationBean registrationBean) throws FailedRegistrationException{
        registerDao.registerNewAccount(registrationBean.getFirstName(), registrationBean.getLastName(), registrationBean.getEmail(),
                                        registrationBean.getPassword(), registrationBean.getRole());
    }

}
