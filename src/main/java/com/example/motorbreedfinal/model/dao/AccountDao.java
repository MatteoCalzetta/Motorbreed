package com.example.motorbreedfinal.model.dao;

import com.example.motorbreedfinal.model.service.Connector;
import com.example.motorbreedfinal.model.service.Query;
import com.example.motorbreedfinal.model.users.Account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AccountDao {

  Statement stmt = null;
  Connection conn = null;

  String role = null;

  public void setAccount(Account account, String email) throws SQLException {
    try {
      conn = Connector.getInstance().getConnection();

      stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      ResultSet rs = Query.checkSignedUserByEmail(stmt, email);

      rs.first(); // LoginDao ha già controllato la password

      account.setFirstName(rs.getString("firstName" )); // settiamo lo stato dell'istanza di Seller/Buyer a runtime
      account.setLastName(rs.getString("lastName"));
      account.setEmail(rs.getString("email"));
      account.setIdAccount(rs.getString("userId"));


  } catch (SQLException e) {
      //not handled. to write
    } finally {
      if(stmt!=null){
        stmt.close();
      }
    }
  }


}



