/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author c0647456
 */
@ManagedBean
@SessionScoped
public class LoginBean {
    private String username = "";
    private String password = "";
    private boolean loggedIn = false;
    private int uid;

    public LoginBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public int getUid() {
        return uid;
    }   
    
    public void checkLoginWithDb(){
        try {
            Connection cn = credentials.dbConnection.getConnection();
            String query = "SELECT U_ID, PASSWORD FROM LOGIN WHERE USERNAME = ?";
            PreparedStatement pstmt = cn.prepareStatement(query);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            String passwordDb="";
            int uidDb=0;
            while(rs.next()){
                passwordDb = rs.getString("password");
                uidDb = rs.getInt("u_id");
            }
            loggedIn = BCrypt.BCrypt.checkpw(this.password, passwordDb);
            if(loggedIn){
                uid = uidDb;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void logout(){
        if(loggedIn){
            loggedIn = false;
        }
    }
}
