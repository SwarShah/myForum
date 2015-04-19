/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author c0647456
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Set<String> keySet = request.getParameterMap().keySet();
        try (PrintWriter out = response.getWriter()) {
            if (keySet.contains("username") && keySet.contains("password")) {
                Connection cn = credentials.dbConnection.getConnection();
                String user = request.getParameter("username");
                String pass = request.getParameter("password");
                String query = "SELECT U_ID, PASSWORD FROM LOGIN WHERE USERNAME = ?";
                PreparedStatement pstmt = cn.prepareStatement(query);
                pstmt.setString(1, user);
                ResultSet rs = pstmt.executeQuery();
                String passwordDb = "";
                int uidDb = 0, uid;
                boolean loggedIn= false;
                while (rs.next()) {
                    passwordDb = rs.getString("password");
                    uidDb = rs.getInt("u_id");
                }
                loggedIn = BCrypt.BCrypt.checkpw(pass, passwordDb);
                if (loggedIn) {
                    uid = uidDb;
                    HttpSession session = request.getSession();
                    session.setAttribute("uid", uidDb);
                    session.setAttribute("loggedIn", loggedIn);
                    response.sendRedirect("index.jsp");
                }
                else{
                    response.sendRedirect("invalidpass.jsp");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
