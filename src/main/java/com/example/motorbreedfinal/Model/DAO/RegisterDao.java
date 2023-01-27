package com.example.motorbreedfinal.Model.DAO;

import com.example.motorbreedfinal.Model.Service.Connector;
import com.example.motorbreedfinal.Model.Service.Query;
import com.example.motorbreedfinal.Model.exceptions.FailedRegistrationException;

import javax.security.auth.login.FailedLoginException;
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
                throw new RuntimeException(e); //auto generata è da fare
            }
        }
    }
}
