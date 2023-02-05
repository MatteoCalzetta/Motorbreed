package com.example.motorbreedfinal.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private static final String USER = "Luigi";
    private static final String USER1 = "root";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ispw";

    private static Connector instance = null;
    private Connection conn = null;
    private Connector() throws SQLException {
        try{
            conn = DriverManager.getConnection(DB_URL, USER, CredentialContainer.getPass());
        }catch(SQLException e){
            conn = DriverManager.getConnection(DB_URL, USER1, CredentialContainer.getPass1());
        }
        System.out.println(conn.isClosed());
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
        try {
            System.out.println(conn.isClosed());
        } catch (SQLException e) {
            //diocane
        }
        return conn;
    }

}