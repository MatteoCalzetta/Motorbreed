package com.example.motorbreedfinal.model.dao;

import com.example.motorbreedfinal.model.Ad;
import com.example.motorbreedfinal.model.Car;
import com.example.motorbreedfinal.model.service.Connector;
import com.example.motorbreedfinal.model.service.Query;
import com.example.motorbreedfinal.model.exceptions.FailedAdInsertionException;

import java.sql.*;

public class InsertionDAO {

    public void insertAd(Ad ad) throws FailedAdInsertionException {
        Statement stmt =null;
        Connection conn =null;

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);


            Query.insertAd(stmt, ad, Integer.parseInt(ad.getCar().getIdCar()), Integer.parseInt(ad.getSeller().getIdAccount()), ad.isPriceCertificated()/*, ad.getImageStream()*/);

        }catch (SQLException e){
            throw new FailedAdInsertionException(e.getMessage());
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
