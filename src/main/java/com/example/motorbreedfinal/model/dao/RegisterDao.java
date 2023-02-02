package com.example.motorbreedfinal.model.dao;

import com.example.motorbreedfinal.model.service.Connector;
import com.example.motorbreedfinal.model.service.Query;
import com.example.motorbreedfinal.model.exceptions.FailedRegistrationException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterDao {
    public void registerNewAccount(String firstName, String lastName, String email, String password, String role) throws FailedRegistrationException {
        Statement stmt=null;
        Connection conn=null;

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.isEmailExisting(stmt, email);
            if(!rs.next()){
                Query.addProfile(stmt, firstName, lastName, email, password, role);
            }
            else{
                throw new FailedRegistrationException("email already registered");
            }
        }
        catch (SQLException se) {

            throw new FailedRegistrationException(se.getMessage());
        }
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                //throw new RuntimeException(e);
            }
        }
    }
}
