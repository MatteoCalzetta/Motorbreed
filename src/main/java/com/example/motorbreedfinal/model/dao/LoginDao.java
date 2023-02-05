package com.example.motorbreedfinal.model.dao;

import com.example.motorbreedfinal.model.service.Connector;
import com.example.motorbreedfinal.model.service.Query;

import javax.security.auth.login.FailedLoginException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDao {
    public String checkCredentials(String email, String password) throws SQLException, FailedLoginException {

        Statement stmt = null;
        Connection conn = null;
        String role = null;

        conn = Connector.getInstance().getConnection();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = Query.checkSignedUserByEmail(stmt, email);

        if (!rs.first()) {       // se rs.first() fallisce non ci sono risultati per la query
            throw new FailedLoginException("Email not registered!");
        }

        rs.first();

        String foundPassword = rs.getString("password");
        if (foundPassword.equals(password)) {
            role = rs.getString("role");
        } else {
            throw new FailedLoginException("Wrong password!");
        }

        stmt.close();



        return role;
    }

}
