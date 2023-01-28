package com.example.motorbreedfinal.model.dao;

import com.example.motorbreedfinal.model.service.Connector;
import com.example.motorbreedfinal.model.service.Query;
import com.example.motorbreedfinal.model.exceptions.FailedProfileCustomizationException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdvancedResearchDAO extends ResearchDAO{
    public void changeFirstname(String newFirstName, String oldFirstname) throws SQLException {

        Statement stmt3=null;
        Connection conn3=null;



        conn3= Connector.getInstance().getConnection();
        stmt3 = conn3.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        Query.updateFirstname(stmt3, newFirstName, oldFirstname);

        try {
            if (stmt3 != null)
                stmt3.close();
        } catch (SQLException se2) {
            //TO-DO
        }
    }

    public void changeLastname(String newLastname, String oldLastname) throws SQLException {

        Statement stmt3=null;
        Connection conn3=null;



        conn3= Connector.getInstance().getConnection();
        stmt3 = conn3.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        Query.updateLastname(stmt3, newLastname, oldLastname);

        try {
            if (stmt3 != null)
                stmt3.close();
        } catch (SQLException se2) {
            //TO-DO
        }
    }

    public void changeEmail(String newUsername, String oldUsername) throws FailedProfileCustomizationException {

        Statement stmt3=null;
        Connection conn3=null;


        try {
            conn3= Connector.getInstance().getConnection();

            stmt3 = conn3.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            //Query.updateEmail(stmt3, newUsername, oldUsername);


        } catch (SQLException e) {
            throw new FailedProfileCustomizationException("This username is already used!",e.getCause());
        } finally {
            try {
                if (stmt3 != null)
                    stmt3.close();
            } catch (SQLException se2) {
                //TO-DO
            }

        }

    }
}
