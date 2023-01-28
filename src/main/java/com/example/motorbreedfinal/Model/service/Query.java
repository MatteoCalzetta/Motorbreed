package com.example.motorbreedfinal.model.service;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        String selectStatement = "SELECT * FROM user WHERE email = " + email;
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet findAdsByBaseFilters(Statement stmt, String brand, String startingPrice, String maxPrice, String startingMileage, String maxMileage) throws SQLException {
        String selectedStatement = "SELECT * FROM ad WHERE true = true ";

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
        System.out.println(selectedStatement);
        return stmt.executeQuery(selectedStatement);
    }

    public static ResultSet findAdsByAdvancedFilters(Statement stmt, String brand, String model, String fuelType, String productionYear, String startingHP, String maxHP, String transmission, String startingPrice, String maxPrice, String startingMileage, String maxMileage, String decorations) throws SQLException {
        String selectedStatement = "SELECT * FROM ad WHERE true = true ";

        if(!startingPrice.isEmpty()){
            selectedStatement += " AND cost > " + startingPrice;
        }
        if(!maxPrice.isEmpty()){
            selectedStatement += " AND cost < " + maxPrice;
        }
        selectedStatement += " AND idcar IN (SELECT idcar FROM car WHERE true = true ";

        if(!model.isEmpty()){
            selectedStatement += " AND model = '" + model + "'";
        }

        if(!fuelType.isEmpty()){
            selectedStatement += " AND fuelType = '" + fuelType + "'";
        }

        if(!productionYear.isEmpty()){
            selectedStatement += " AND productionYear = '" + productionYear + "'";
        }

        if(!startingHP.isEmpty()){
            selectedStatement += " AND horsePower > '" + startingHP + "'";
        }

        if(!maxHP.isEmpty()){
            selectedStatement += " AND horsePower < '" + maxHP + "'";
        }

        if(!transmission.isEmpty()){
            selectedStatement += " AND transmission = '" + transmission + "'";
        }

        if(!brand.isEmpty()){
            selectedStatement += " AND brand = '" + brand + "'";
        }
        if(!startingMileage.isEmpty()){
            selectedStatement += " AND mileage > " + startingMileage;
        }
        if(!maxMileage.isEmpty()){
            selectedStatement += " AND mileage < " + maxMileage;
        }
        if(!decorations.isEmpty()){
            selectedStatement += " AND decorations = '" + decorations + "'";
        }

        selectedStatement += ")";
        System.out.println(selectedStatement);
        return stmt.executeQuery(selectedStatement);
    }

    public static ResultSet findCarById(Statement stmt, String carId) throws  SQLException{
        String selectStatement = "SELECT * FROM car WHERE idcar = " + carId;
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet findSellerById(Statement stmt, String userId) throws  SQLException{
        String selectStatement = "SELECT * FROM user WHERE userid = " + userId;
        System.out.println(selectStatement);
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
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet findMyOrders(Statement stmt, String userId) throws SQLException{
        String selectStatement = String.format("SELECT * FROM buyer_order WHERE userid = %s'", userId);
        return stmt.executeQuery(selectStatement);
    }


    public static ResultSet findBuyerOrders(Statement stmt, String userId) throws SQLException {
        String selectStatement = String.format("SELECT * FROM AD WHERE sold = 1 AND idAd IN (SELECT idAd FROM buyer_order WHERE idBuyer = '%s')", userId);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static void insertAd(Statement stmt, int cost, String description, String location, String insertionDate, int carId, int sellerId, boolean priceCertification, InputStream imageStream) throws  SQLException{
        String insertStatement = String.format("INSERT INTO ad (Cost, Description, Location, InsertionDate, numberofclicks, certification, image, sold, idcar, idseller) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')", cost, description, location, insertionDate, 0, Boolean.compare(priceCertification, true)+1, imageStream, 0, carId, sellerId);
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet getCarId(Statement stmt) throws SQLException {
        String selectStatement = "SELECT MAX(idcar) from car";
        return stmt.executeQuery(selectStatement);
    }

    public static void insertCar(Statement stmt, String carId, String licencePlate, String transmission, String immatricolationYear, String brand, String model, String mileage, String productionYear, String horsepower, String fuelType, String decorations) throws  SQLException{
        String insertStatement= String.format("INSERT INTO car (idcar, licencePlate, transmission,  immatricolationYear, brand, model, mileage, productionYear, horsePower, fuelType, decorations) VALUES ('%s', '%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')", carId, licencePlate, transmission,immatricolationYear, brand,model, mileage,productionYear, horsepower, fuelType,decorations);
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
        System.out.println(deleteStatement);
        stmt.executeUpdate(deleteStatement);
    }

    public static void updateEmail(Statement stmt, String newEmail, String oldEmail) throws SQLException {
        String updateStatement = String.format("UPDATE user SET email = '%s' WHERE email = '%s'", newEmail, oldEmail);
        stmt.executeUpdate(updateStatement);
    }
}

