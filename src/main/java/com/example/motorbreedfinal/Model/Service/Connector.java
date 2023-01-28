package com.example.motorbreedfinal.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private static final String USER = "Luigi";
    private static final String PASS = "5headLmao";
    private static final String USER1 = "root";
    private static final String PASS1 = "root1234";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ispw";

    private static Connector instance = null;
    private Connection conn = null;
    private Connector() throws SQLException {
        try{
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch(SQLException e){
            conn = DriverManager.getConnection(DB_URL, USER1, PASS1);
            //e.printStackTrace(); togliere il commento nella versione finale
        }
    }

    public static Connector getInstance() throws SQLException {
        if(instance == null){
            try {
                instance = new Connector();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return instance;
    }

    public Connection getConnection(){
        return conn;
    }

}