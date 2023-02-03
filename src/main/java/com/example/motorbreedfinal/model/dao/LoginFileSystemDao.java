package com.example.motorbreedfinal.model.dao;

import javafx.scene.control.TableRow;

import javax.security.auth.login.FailedLoginException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class LoginFileSystemDao {

    public String checkCredentials(String email, String password) throws FailedLoginException {
        File file = new File("C:\\Users\\Luigi\\Desktop\\Users.txt");
        String str;

        String role;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

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
