package com.example.motorbreedfinal.model.dao;

import com.example.motorbreedfinal.model.Ad;
import com.example.motorbreedfinal.model.service.Connector;
import com.example.motorbreedfinal.model.service.Query;
import com.example.motorbreedfinal.model.exceptions.FailedResearchException;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResearchDAO {

    CarDAO carDAO = new CarDAO();
    SellerDao sellerDao = new SellerDao();

    public List<Ad> findAdsByBaseFilter(String brand, String startingPrice, String maxPrice, String startingMileage, String maxMileage) throws FailedResearchException {
        Statement stmt = null;
        Connection conn = null;
        List<Ad> ads = new ArrayList<>();

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.findAdsByBaseFilters(stmt, brand, startingPrice, maxPrice, startingMileage, maxMileage);
            while (rs.next()) {
                ads.add(extractAd(conn, rs));
            }
        } catch (SQLException | IOException se) {
            se.printStackTrace();
            throw new FailedResearchException("An error during research occurred, wrong filters or no car corresponding to what you are searching for.");
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e); //auto generata è da fare
            }
        }
        return ads;
    }

    public List<Ad> findAdsByAdvancedFilter(String brand, String model, String fuelType, String productionYear, String startingHP, String maxHP, String transmission, String startingPrice, String maxPrice, String startingMileage, String maxMileage, String decorations) throws FailedResearchException {
        Statement stmt = null;
        Connection conn = null;
        List<Ad> ads = new ArrayList<>();

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.findAdsByAdvancedFilters(stmt, brand, model, fuelType, productionYear, startingHP, maxHP, transmission, startingPrice, maxPrice, startingMileage, maxMileage, decorations);
            while (rs.next()) {
                ads.add(extractAd(conn, rs));
            }
        } catch (SQLException | IOException se) {
            se.printStackTrace();
            throw new FailedResearchException("An error during research occurred, wrong filters or no car corresponding to what you are searching for.");
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e); //auto generata è da fare
            }
        }
        return ads;
    }

    public List<Ad> findFavoriteAds(String userId){
        Statement stmt = null;
        Connection conn = null;
        List<Ad> ads = new ArrayList<>();

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.findFavoriteAds(stmt, userId);
            while (rs.next()) {
                ads.add(extractAd(conn, rs));
            }
        } catch (SQLException | FailedResearchException | IOException se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e); //auto generata è da fare
            }
        }
        return ads;
    }

    public List<Ad> findBuyerOrders(String userId) {
        Statement stmt = null;
        Connection conn = null;
        List<Ad> ads = new ArrayList<>();

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.findBuyerOrders(stmt, userId);
            while (rs.next()) {
                ads.add(extractAd(conn, rs));
            }
        } catch (SQLException | FailedResearchException | IOException se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e); //auto generata è da fare
            }
        }
        return ads;
    }
    private Ad extractAd(Connection conn, ResultSet rs) throws SQLException, FailedResearchException, IOException {
        Ad ad = new Ad();
        ad.setIdAd(rs.getString(1));
        ad.setCost(rs.getInt(2));
        ad.setDescription(rs.getString(3));
        ad.setLocation(rs.getString(4));
        ad.setInsertionDate(rs.getString(5));
        ad.setNumberOfClicks(rs.getInt(6));
        Blob bl = rs.getBlob(8);




        if(bl != null) {
            InputStream inputStream = bl.getBinaryStream();
            Image image = new Image(inputStream);
            ad.setImage(image);
        }
        ad.setCar(carDAO.findCarById(conn, rs.getString(10)));
        ad.setSeller(sellerDao.findSellerById(conn,rs.getString(11)));
        return ad;
    }

    public void addFavourites(String idAd, String idBuyer) {
        Statement stmt = null;
        Connection conn = null;
        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.isAdFavorite(stmt, idAd, idBuyer);
            if(rs.next()){
                Query.remoteFavorites(stmt, idAd, idBuyer);
            } else{
                Query.addFavourites(stmt, idAd, idBuyer);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e); //auto generata è da fare
            }
        }
    }
}