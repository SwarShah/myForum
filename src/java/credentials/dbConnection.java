/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package credentials;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author c0647456
 */
public class dbConnection {
    public static Connection getConnection(){
        Connection cn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        String url = "jdbc:mysql://localhost:3306/myforum";
        try {
            cn = DriverManager.getConnection(url, "root", "");
        } catch (SQLException ex) {
            System.out.println("Failed to connect to database");
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cn;
    }
}
