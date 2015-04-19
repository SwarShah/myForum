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
@WebServlet(name = "NewPostServlet", urlPatterns = {"/NewPostServlet"})
public class NewPostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Set<String> keySet = request.getParameterMap().keySet();
        try (PrintWriter out = response.getWriter()) {
            if (keySet.contains("postContent") && keySet.contains("uid") && keySet.contains("tid")) {
                Connection cn = credentials.dbConnection.getConnection();
                String postContent = request.getParameter("postContent");
                String uid = request.getParameter("uid");
                String tid = request.getParameter("tid");
                String query = "insert into post(description, t_id, u_id, c_id) values( ?, ?, ?, (SELECT c_id from thread where t_id = ?));";
                PreparedStatement pstmt = cn.prepareStatement(query);
                pstmt.setString(1, postContent);
                pstmt.setString(2, tid);
                pstmt.setString(3, uid);
                pstmt.setString(4, tid);
                int x = pstmt.executeUpdate();
                if (x == 1) {
                    response.sendRedirect("showPost.jsp?tid="+tid);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
