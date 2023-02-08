package com.example.motorbreedfinal.model.service;

import com.example.motorbreedfinal.model.Ad;
import com.example.motorbreedfinal.model.Car;

import java.sql.*;

public class Query {

    private Query() {}

    public static ResultSet checkSignedUserByEmail(Statement stmt, String credential) throws SQLException {
        String selectStatement = String.format("SELECT * FROM user WHERE (email = '%s')", credential);
        return stmt.executeQuery(selectStatement);
    }

    public static void addProfile(Statement stmt, String firstName, String lastName, String email, String password, String role) throws SQLException {
        String insertStatement = String.format("INSERT INTO `user`(firstname,lastname,email,password,role) VALUES ('%s','%s','%s','%s','%s')", firstName,lastName,email,password,role);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet isEmailExisting(Statement stmt, String email) throws  SQLException{
        String selectStatement = String.format("SELECT * FROM user WHERE email = '%s'", email);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet findAdsByBaseFilters(Statement stmt, String brand, String startingPrice, String maxPrice, String startingMileage, String maxMileage) throws SQLException {
        String selectedStatement = "SELECT * FROM ad WHERE sold = '0' ";

        if(!startingPrice.isEmpty()){
            selectedStatement += " AND cost > " + startingPrice;
        }
        if(!maxPrice.isEmpty()){
            selectedStatement += " AND cost < " + maxPrice;
        }
        selectedStatement += " AND idcar IN (SELECT idcar FROM car WHERE true = true ";

        if(!brand.isEmpty()){
            selectedStatement += " AND brand = '" + brand + "'";
        }
        if(!startingMileage.isEmpty()){
            selectedStatement += " AND mileage > " + startingMileage;
        }
        if(!maxMileage.isEmpty()){
            selectedStatement += " AND mileage < " + maxMileage;
        }
        selectedStatement += ")";
        return stmt.executeQuery(selectedStatement);
    }

    public static ResultSet findAdsByAdvancedFilters(Statement stmt, Car car, String maxHP, String startingPrice, String maxPrice, String maxMileage) throws SQLException {
        String selectedStatement = "SELECT * FROM ad WHERE sold = '0' ";

        if(!startingPrice.isEmpty()){
            selectedStatement += " AND cost > " + startingPrice;
        }
        if(!maxPrice.isEmpty()){
            selectedStatement += " AND cost < " + maxPrice;
        }
        selectedStatement += " AND idcar IN (SELECT idcar FROM car WHERE true = true ";

        if(!car.getModel().isEmpty()){
            selectedStatement += " AND model = '" + car.getModel() + "'";
        }

        if(!car.getFuelType().isEmpty()){
            selectedStatement += " AND fuelType = '" + car.getFuelType()+ "'";
        }

        if(!car.getProductionYear().isEmpty()){
            selectedStatement += " AND productionYear = '" + car.getProductionYear() + "'";
        }

        if(!String.valueOf(car.getHorsepower()).isEmpty()){
            selectedStatement += " AND horsePower > '" + car.getHorsepower() + "'";
        }

        if(!maxHP.isEmpty()){
            selectedStatement += " AND horsePower < '" + maxHP + "'";
        }

        if(!car.getTransmission().isEmpty()){
            selectedStatement += " AND transmission = '" + car.getTransmission() + "'";
        }

        if(!car.getBrand().isEmpty()){
            selectedStatement += " AND brand = '" + car.getBrand() + "'";
        }
        if(!String.valueOf(car.getMileage()).isEmpty()){
            selectedStatement += " AND mileage > " + car.getMileage();
        }
        if(!maxMileage.isEmpty()){
            selectedStatement += " AND mileage < " + maxMileage;
        }
        if(!car.getDecorations().isEmpty()){
            selectedStatement += " AND decorations = '" + car.getDecorations() + "'";
        }

        selectedStatement += ")";
        return stmt.executeQuery(selectedStatement);
    }

    public static ResultSet findSellerAds(Statement stmt, String userId) throws SQLException {
        String selectStatement = String.format("SELECT * FROM ad WHERE sold = '0' AND idseller = '%s'", userId);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet findCarById(Statement stmt, String carId) throws  SQLException{
        String selectStatement = "SELECT * FROM car WHERE idcar = " + carId;
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet findSellerById(Statement stmt, String userId) throws  SQLException{
        String selectStatement = "SELECT * FROM user WHERE userid = " + userId;
        return stmt.executeQuery(selectStatement);
    }

    public static void updateFirstname(Statement stmt, String newFirstname, String email) throws SQLException {
        String updateStatement = String.format("UPDATE user SET firstName = '%s' WHERE email = '%s'", newFirstname, email);
        stmt.executeUpdate(updateStatement);
    }

    public static void updateLastname(Statement stmt, String newLastname, String email) throws SQLException {
        String updateStatement = String.format("UPDATE user SET lastName = '%s' WHERE email = '%s'", newLastname, email);
        stmt.executeUpdate(updateStatement);
    }


    public static ResultSet findFavoriteAds(Statement stmt, String userId) throws  SQLException{
        String selectStatement = String.format("SELECT * FROM AD WHERE sold = 0 AND idAd IN (SELECT idAd FROM buyer_favorites WHERE idBuyer = '%s')", userId);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet findMyOrders(Statement stmt, String userId) throws SQLException{
        String selectStatement = String.format("SELECT * FROM buyer_order WHERE userid = %s'", userId);
        return stmt.executeQuery(selectStatement);
    }


    public static ResultSet findBuyerOrders(Statement stmt, String userId) throws SQLException {
        String selectStatement = String.format("SELECT * FROM AD WHERE sold = 1 AND idAd IN (SELECT idAd FROM buyer_order WHERE idBuyer = '%s')", userId);
        return stmt.executeQuery(selectStatement);
    }

    public static void insertAd(Statement stmt, Ad ad, int carId, int sellerId, boolean priceCertification/*, FileInputStream imageStream*/) throws  SQLException{
        String insertStatement = String.format("INSERT INTO ad (Cost, Description, Location, InsertionDate, numberofclicks, certification, sold, idcar, idseller) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s')", ad.getAdCost(), ad.getAdDescription(), ad.getAdLocation(), ad.getInsertionDate(), 0, Boolean.compare(priceCertification, true)+1, 0, carId, sellerId);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet getCarId(Statement stmt) throws SQLException {
        String selectStatement = "SELECT MAX(idcar) from car";
        return stmt.executeQuery(selectStatement);
    }

    public static void insertCar(Statement stmt, Car car) throws  SQLException{
        String insertStatement= String.format("INSERT INTO car (idcar, licencePlate, transmission, insurance, immatricolationYear, brand, model, mileage, productionYear, horsePower, fuelType, decorations) VALUES ('%s', '%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')", car.getIdCar(), car.getLicencePlate(), car.getTransmission(), Boolean.compare(car.isInsurance(), true)+1,car.getImmatricolationYear(), car.getBrand(),car.getModel(), car.getMileage(), car.getProductionYear(), car.getHorsepower(), car.getFuelType(), car.getDecorations());
        stmt.executeUpdate(insertStatement);
    }

    public static void addFavourites(Statement stmt, String idAd, String idBuyer) throws SQLException {
        String insertStatement = String.format("INSERT into buyer_favorites (idBuyer, idAd) VALUES ('%s','%s')", idBuyer, idAd);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet isAdFavorite(Statement stmt, String idAd, String idBuyer) throws SQLException {
        String selectStatement = String.format("SELECT * FROM buyer_favorites WHERE idBuyer = '%s' AND idAd = '%s'", idBuyer, idAd);
        return stmt.executeQuery(selectStatement);
    }

    public static void remoteFavorites(Statement stmt, String idAd, String idBuyer) throws SQLException {
        String deleteStatement = String.format("DELETE FROM buyer_favorites WHERE idBuyer = '%s' AND idAd = '%s';", idBuyer, idAd);
        stmt.executeUpdate(deleteStatement);
    }

    public static void updateEmail(Statement stmt, String newEmail, String oldEmail) throws SQLException {
        String updateStatement = String.format("UPDATE user SET email = '%s' WHERE email = '%s'", newEmail, oldEmail);
        stmt.executeUpdate(updateStatement);
    }

    public static ResultSet findAdById(Statement stmt, String idAd) throws SQLException {
        String selectStatement = String.format("SELECT * FROM AD WHERE idAd = '%s'", idAd);
        return stmt.executeQuery(selectStatement);
    }

    public static void addOrder(Statement stmt, String idAd, String idBuyer) throws SQLException {
        String insertStatement = String.format("INSERT into buyer_order (idBuyer, idAd) VALUES ('%s' '%s')", idBuyer, idAd);
        stmt.executeUpdate(insertStatement);
        String updateStatement = String.format("UPDATE ad SET sold = '1' WHERE idAd = '%s'", idAd);
        stmt.executeUpdate(updateStatement);
    }

    public static ResultSet findIdAd(Statement stmt, String licencePlate) throws SQLException {
        String selectStatement = String.format("SELECT * FROM ad WHERE idCar IN (SELECT idCar FROM car WHERE licencePlate = '%s')", licencePlate);
        return stmt.executeQuery(selectStatement);
    }

    public static void updateAd(Statement stmt, Ad ad) throws SQLException {
        String updateStatement = String.format("UPDATE car SET licencePlate = '%s', transmission = '%s', insurance = '%s', immatricolationYear = '%s', brand = '%s', model = '%s', mileage = '%s', productionYear = '%s', horsePower = '%s', fuelType = '%s', decorations = '%s' WHERE idcar = '%s'", ad.getCar().getLicencePlate(), ad.getCar().getTransmission(), Boolean.compare(ad.getCar().isInsurance(), true)+1, ad.getCar().getImmatricolationYear(), ad.getCar().getBrand(), ad.getCar().getModel(), ad.getCar().getMileage(), ad.getCar().getProductionYear(), ad.getCar().getHorsepower(), ad.getCar().getFuelType(),ad.getCar().getDecorations(), ad.getCar().getIdCar());
        stmt.executeUpdate(updateStatement);
        updateStatement = String.format("UPDATE ad SET Cost = '%s', Description = '%s', Location = '%s', InsertionDate = '%s', numberofclicks = '%s', certification = '%s', sold = '%s', idcar = '%s', idseller = '%s' WHERE idAd = '%s'", ad.getAdCost(), ad.getAdDescription(), ad.getAdLocation(), ad.getInsertionDate(), ad.getNumberOfClicks(), Boolean.compare(ad.isPriceCertificated(), true)+1, 0, ad.getCar().getIdCar(), ad.getSeller().getIdAccount(),ad.getIdAd());
        stmt.executeUpdate(updateStatement);
    }


}

