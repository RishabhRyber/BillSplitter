package utils;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rishabh
 */
public class DbConnection {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/expenses";
    static final String USER = "root";
    static final String PASS = "";
    Connection conn = null;
    Statement stmt = null;

    public DbConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("excepion aya");
        }
    }

    public void closeCon() {
        try {
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet runQuery(String sql) {
        try {
            ResultSet rs = stmt.executeQuery(sql);
            return rs;

        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean runUpdate(String sql) {
        try {
            stmt.executeUpdate(sql);

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}//end FirstExample
