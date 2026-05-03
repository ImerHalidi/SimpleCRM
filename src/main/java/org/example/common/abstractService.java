package org.example.common;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class abstractService {

    protected Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection();
    }
    protected void close(Connection con, PreparedStatement ps){
        DatabaseConnection.close(con,ps);
    }

    protected void close(Connection con, PreparedStatement ps, ResultSet rs){
        DatabaseConnection.close(con,ps,rs);
    }

    protected  RuntimeException handleException(Exception e){
        e.printStackTrace();
        return new RuntimeException(e.getMessage());
    }


}
