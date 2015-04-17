/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
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

/**
 *
 * @author c0647456
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Set<String> keySet = request.getParameterMap().keySet();
        try (PrintWriter out = response.getWriter()) {
            if (keySet.contains("email") && keySet.contains("password")) {
                Connection cn = credentials.dbConnection.getConnection();
                String email = request.getParameter("email");
                //Encrypting password
                String hashedPassword = BCrypt.BCrypt.hashpw(request.getParameter("password"), BCrypt.BCrypt.gensalt());
                String query = "INSERT INTO login (email, password) VALUES (?, ?)";
                PreparedStatement pstmt = cn.prepareStatement(query);
                pstmt.setString(1, email);
                pstmt.setString(2, hashedPassword);
                int x = pstmt.executeUpdate();
                if(x==1)
                    response.sendRedirect("index.html");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
