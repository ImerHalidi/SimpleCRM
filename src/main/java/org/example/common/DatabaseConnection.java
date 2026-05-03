package org.example.common;

import javax.xml.transform.Result;
import java.sql.*;

public class DatabaseConnection {
    private static final String Url = "jdbc:mysql://http://localhost/phpmyadmin/index.php?route=/database/structure&db=simple_crm";
    private static final String Username = "root";
    private static final String Password = "";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(Url,Username,Password);
    }

    public static void close(Connection con , PreparedStatement ps) {
        try{
            if(ps!= null )ps.close();
        } catch (SQLException e) {}

        try {
            if(con!=null)con.close();
        } catch (SQLException e) {

        }

    }

    public static void close(Connection con, PreparedStatement ps, ResultSet rs){
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {

        }
        close(con, ps);
        }


}
