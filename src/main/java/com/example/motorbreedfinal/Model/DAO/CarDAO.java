package com.example.motorbreedfinal.Model.DAO;

import com.example.motorbreedfinal.Model.Car;
import com.example.motorbreedfinal.Model.Service.Query;
import com.example.motorbreedfinal.Model.exceptions.FailedResearchException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CarDAO {
    public Car findCarById(Connection conn, String idCar) throws FailedResearchException {
        Statement stmt = null;
        Car car = null;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.findCarById(stmt, idCar);
            if(rs.next()) {
               car = extractCar(rs);
            }
        } catch (SQLException se) {
            se.printStackTrace();
            throw new FailedResearchException(se.getMessage());
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e); //auto generata è da fare
            }
        }
        return car;
    }

    private Car extractCar(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setIdCar(rs.getString(1));
        car.setLicencePlate(rs.getString(2));
        car.setTransmission(rs.getString(3));
        car.setInsurance(rs.getBoolean(4));
        car.setImmatricolationYear(rs.getString(5));
        car.setBrand(rs.getString(6));
        car.setModel(rs.getString(7));
        car.setMileage(rs.getInt(8));
        car.setProductionYear(rs.getString(9));
        car.setHorsepower(rs.getInt(10));
        car.setFuelType(rs.getString(11));
        car.setDecorations(rs.getString(12));
        return car;
    }


}
