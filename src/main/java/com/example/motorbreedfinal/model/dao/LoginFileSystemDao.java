package com.example.motorbreedfinal.model.dao;

import javax.security.auth.login.FailedLoginException;
import java.io.*;


public class LoginFileSystemDao {

    public String checkCredentials(String email, String password) throws FailedLoginException {
        String url = "C:\\Users\\Luigi\\Desktop\\Users.txt";

        File file = new File(url);
        String str;

        String role;

        try (BufferedReader br = new BufferedReader(new FileReader(file))){

            while ((str = br.readLine())!= null){
                if(str.equals(email)){
                    str = br.readLine();
                    if(str.equals(password)){
                        role = br.readLine();
                        return role;
                    }else {
                        throw new FailedLoginException("Wrong password");
                    }
                }
            }

            throw new FailedLoginException("Email not registered!");

        }catch (IOException e){

            throw new FailedLoginException("Failed Login");
        }
    }

}
