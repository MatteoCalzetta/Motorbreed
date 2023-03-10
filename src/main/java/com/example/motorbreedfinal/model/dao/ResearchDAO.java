package com.example.motorbreedfinal.model.dao;

import com.example.motorbreedfinal.model.Ad;
import com.example.motorbreedfinal.model.Car;
import com.example.motorbreedfinal.model.service.Connector;
import com.example.motorbreedfinal.model.service.Query;
import com.example.motorbreedfinal.model.exceptions.FailedResearchException;

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
        } catch (Exception se) {
            se.printStackTrace();
            throw new FailedResearchException("An error during research occurred, wrong filters or no car corresponding to what you are searching for.");
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                //not handled
            }
        }
        return ads;
    }

    public List<Ad> findAdsByAdvancedFilter(Car car, String maxHP, String startingPrice, String maxPrice, String maxMileage) throws FailedResearchException {
        Statement stmt = null;
        Connection conn = null;
        List<Ad> ads = new ArrayList<>();

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.findAdsByAdvancedFilters(stmt, car, maxHP, startingPrice, maxPrice, maxMileage);
            while (rs.next()) {
                ads.add(extractAd(conn, rs));
            }
        } catch (SQLException se) {
            se.printStackTrace();
            throw new FailedResearchException("An error during research occurred, wrong filters or no car corresponding to what you are searching for.");
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                //not handled
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
        } catch (SQLException | FailedResearchException se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                //not handled
            }
        }
        return ads;
    }

    public Ad findAdById(String idAd, String idBuyer) throws SQLException {

        Ad ad = new Ad();
        Connection conn = Connector.getInstance().getConnection();
        try ( Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            String insertStatement = String.format("INSERT into buyer_order (idBuyer, idAd) VALUES ('%s','%s')", idBuyer, idAd);
            stmt.executeUpdate(insertStatement);
            String updateStatement = String.format("UPDATE ad SET sold = '1' WHERE idAd = '%s'", idAd);
            stmt.executeUpdate(updateStatement);
            ResultSet rs = Query.findAdById(stmt, idAd);
            while (rs.next()) {
                ad = extractAd(conn, rs);
            }
        } catch (SQLException | FailedResearchException se) {
            se.printStackTrace();
        }
        return ad;
    }

    public List<Ad> findBuyerOrders(String userId) {

        List<Ad> ads = new ArrayList<>();

        try {
            Connection conn = Connector.getInstance().getConnection();
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.findBuyerOrders(stmt, userId);
            while (rs.next()) {
                ads.add(extractAd(conn, rs));
            }

        } catch (SQLException | FailedResearchException se) {
            se.printStackTrace();
        }
        return ads;
    }
    private Ad extractAd(Connection conn, ResultSet rs) throws SQLException, FailedResearchException{
        Ad ad = new Ad();
        ad.setIdAd(rs.getString(1));
        ad.setAdCost(rs.getInt(2));
        ad.setAdDescription(rs.getString(3));
        ad.setAdLocation(rs.getString(4));
        ad.setInsertionDate(rs.getString(5));
        ad.setNumberOfClicks(rs.getInt(6));
        ad.setPriceCertification(rs.getInt(7) == 1);
        ad.setCar(carDAO.findCarById(conn, rs.getString(10)));
        ad.setSeller(sellerDao.findSellerById(conn,rs.getString(11)));
        return ad;
    }
    public void addFavourites(String idAd, String idBuyer) throws SQLException {
        Connection conn = Connector.getInstance().getConnection();
        try(
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            ResultSet rs = Query.isAdFavorite(stmt, idAd, idBuyer);
            if(rs.next()){
                Query.remoteFavorites(stmt, idAd, idBuyer);
            } else{
                Query.addFavourites(stmt, idAd, idBuyer);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public List<Ad> findSellerAds(String userId) throws SQLException {

        List<Ad> ads = new ArrayList<>();
        Connection conn = Connector.getInstance().getConnection();
        try (
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){

            ResultSet rs = Query.findSellerAds(stmt, userId);
            while (rs.next()) {
                ads.add(extractAd(conn, rs));
            }
        } catch (SQLException | FailedResearchException se) {
            se.printStackTrace();
        }
        return ads;
    }
}
