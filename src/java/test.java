/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = {"/test"})
public class test extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // session.setAttribute("uid", uidDb);
         //           session.setAttribute("loggedIn", loggedIn);
        HttpSession session = request.getSession(false);
        boolean b = (boolean) session.getAttribute("loggedIn");
        System.out.println(b);
        
    }


}
