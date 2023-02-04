package com.example.motorbreedfinal.model.dao;

import com.example.motorbreedfinal.model.users.Account;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class AccountFileSystemDao {

    public void setAccount(Account account, String email) {

        String url = "C:\\Users\\Luigi\\Desktop\\Users.txt";

        File file = new File(url);
        String str;

        try (BufferedReader br = new BufferedReader(new FileReader(file))){

            while ((str = br.readLine())!= null){

                if(str.equals(email)){
                    account.setEmail(email);
                    str = br.readLine();
                    str = br.readLine();
                    account.setFirstName(br.readLine());
                    account.setLastName(br.readLine());
                    account.setIdAccount(br.readLine());
                }
            }

        }catch (IOException e){
            //unhandled
        }

    }
}
