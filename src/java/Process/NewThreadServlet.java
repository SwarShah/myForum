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
import java.sql.SQLException;
import java.util.Date;
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
@WebServlet(name = "NewThread", urlPatterns = {"/NewThread"})
public class NewThreadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Set<String> keySet = request.getParameterMap().keySet();
        try (PrintWriter out = response.getWriter()) {
            if (keySet.contains("title") && keySet.contains("cid") && keySet.contains("uid")) {
                Connection cn = credentials.dbConnection.getConnection();
                String title = request.getParameter("title");
                String uid = request.getParameter("uid");
                String cid = request.getParameter("cid");
                String query = "insert into thread(description, date, title, c_id, u_id) values( ?, NOW(), ?, ?, ?);";
                PreparedStatement pstmt = cn.prepareStatement(query);
                Date d = new Date();
                pstmt.setString(1, title);
                pstmt.setString(2, title);
                pstmt.setString(3, cid);
                pstmt.setString(4, uid);
                int x = pstmt.executeUpdate();
                if (x == 1) {
                    response.sendRedirect("showThread.jsp?cid=" + cid);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
