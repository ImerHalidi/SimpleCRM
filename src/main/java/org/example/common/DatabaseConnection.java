package org.example.common;


import java.sql.*;

public class DatabaseConnection {
    private static final String Url = "jdbc:mysql://localhost:3306/simple_crm";
    private static final String Username = "root";
    private static final String Password = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found", e);
        }

        return DriverManager.getConnection(Url, Username, Password);
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
