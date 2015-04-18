/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.sql.Connection;
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
        Connection cn = credentials.dbConnection.getConnection();
        
    }
}
