package com.example.motorbreedfinal.model.dao;

import com.example.motorbreedfinal.model.Ad;
import com.example.motorbreedfinal.model.Car;
import com.example.motorbreedfinal.model.service.Connector;
import com.example.motorbreedfinal.model.service.Query;
import com.example.motorbreedfinal.model.exceptions.FailedAdInsertionException;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertionDAO {

    public void insertAd(Ad ad, String idSeller, InputStream imageStream) throws FailedAdInsertionException {
        Statement stmt =null;
        Connection conn =null;

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //System.out.println("il car id prima di fare insert ad è " + ad.getCar().getIdCar());
            Query.insertAd(stmt, ad.getCost(), ad.getDescription(), ad.getLocation(), ad.getInsertionDate(), Integer.parseInt(ad.getCar().getIdCar()), Integer.parseInt(ad.getSeller().getIdAccount()), ad.isPriceCertificated());

            ResultSet rs = Query.findIdAd(stmt, ad.getCar().getLicencePlate());
            rs.next();
            int idAd = rs.getInt(1);

            Query.insertImage(conn, imageStream, idAd);

        }catch (SQLException e){
            throw new FailedAdInsertionException(e.getMessage());
        }
        try {
            if (stmt != null)
                stmt.close();
        } catch (SQLException se2) {
            //TO-DO
        }
    }



    public void insertCar(Car car) throws FailedAdInsertionException, SQLException {
        Statement stmt =null;
        Connection conn =null;
        ResultSet rs;

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            Query.insertCar(stmt, car.getIdCar(), car.getLicencePlate(), car.getTransmission(), car.isInsurance(), car.getImmatricolationYear(), car.getBrand(),car.getModel(), String.valueOf(car.getMileage()), car.getProductionYear(), String.valueOf(car.getHorsepower()), car.getFuelType(), car.getDecorations());

        }catch (SQLException e){
            throw new FailedAdInsertionException(e.getMessage());
        }
        try {
            if (stmt != null)
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
            //rs.close();
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
