package com.example.motorbreedfinal.model.dao;

import com.example.motorbreedfinal.model.service.Query;
import com.example.motorbreedfinal.model.users.Seller;
import com.example.motorbreedfinal.model.exceptions.FailedResearchException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SellerDao extends AccountDao {


    private void retrieveSellerRatings() {
    }

    private void retrieveSellerAds() {
    }

    private void retrieveSellerInfo() {
    }

    public Seller findSellerById(Connection conn, String idSeller) throws FailedResearchException {
        Statement stmt = null;
        Seller seller = null;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.findSellerById(stmt, idSeller);
            if(rs.next()) {
                seller = extractSeller(rs);
            }
        } catch (SQLException se) {
            throw new FailedResearchException(se.getMessage());
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e); //auto generata è da fare
            }
        }
        return seller;
    }

    private Seller extractSeller(ResultSet rs) throws SQLException {
        Seller seller = new Seller();
        seller.setEmail(rs.getString(1));
        seller.setIdAccount(rs.getString(3));
        seller.setFirstName(rs.getString(4));
        seller.setLastName(rs.getString(5));
        return seller;
    }


}
