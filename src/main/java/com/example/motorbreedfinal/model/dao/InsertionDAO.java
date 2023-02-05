package com.example.motorbreedfinal.model.dao;

import com.example.motorbreedfinal.model.Ad;
import com.example.motorbreedfinal.model.Car;
import com.example.motorbreedfinal.model.service.Connector;
import com.example.motorbreedfinal.model.service.Query;
import com.example.motorbreedfinal.model.exceptions.FailedAdInsertionException;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;

public class InsertionDAO {

    public void insertAd(Ad ad) throws FailedAdInsertionException {
        Statement stmt =null;
        Connection conn =null;

        LocalDateTime dateTime = LocalDateTime.now();

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            /*String sql = "INSERT INTO ad (idcar, idseller, certification, image, sold, numberofclicks, InsertionDate, Location, Description, Cost) VALUES (?, ?, ?, ?, ?, ?, ? ,?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setInt(1, Integer.parseInt(ad.getCar().getIdCar()));
            preparedStmt.setInt(2, Integer.parseInt(ad.getSeller().getIdAccount()));
            preparedStmt.setBoolean(3, ad.isPriceCertificated());
            preparedStmt.setBlob(4, ad.getImageStream());
            preparedStmt.setInt(5, 0);
            preparedStmt.setInt(6,0);
            preparedStmt.setString(7, String.valueOf(dateTime));
            preparedStmt.setString(8, ad.getAdLocation());
            preparedStmt.setString(9, ad.getAdDescription());
            preparedStmt.setInt(10, ad.getAdCost());
            preparedStmt.execute();*/

            Query.insertAd(stmt, ad, Integer.parseInt(ad.getCar().getIdCar()), Integer.parseInt(ad.getSeller().getIdAccount()), ad.isPriceCertificated()/*, ad.getImageStream()*/);

        }catch (SQLException e){
            throw new FailedAdInsertionException(e.getMessage());
        }finally {
            try {
                if (ad.getImageStream() != null) {
                    ad.getImageStream().close();
                }
            } catch (IOException e) {
                // Handle the exceptions
            }
        }
        try {
            stmt.close();
        } catch (SQLException se2) {
            //TO-DO
        }
    }



    public void insertCar(Car car) throws FailedAdInsertionException, SQLException {
        Statement stmt =null;
        Connection conn =null;

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            Query.insertCar(stmt, car);

        }catch (SQLException e){
            throw new FailedAdInsertionException(e.getMessage());
        }
        try {
            stmt.close();
        } catch (SQLException se2) {
            //TO-DO
        }
    }

    public int getCarId() throws FailedAdInsertionException, SQLException {
        Statement stmt =null;
        Connection conn =null;
        int idCar = 0;

        try{
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement();
            String query = "SELECT MAX(idcar) FROM car";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            idCar = rs.getInt("MAX(idcar)") + 1;
        } catch(SQLException s){
            s.printStackTrace();
        }

        try {
            if (stmt != null)
                stmt.close();
        } catch (SQLException se2) {
            //TO-DO
        }

        return idCar;
    }

}
