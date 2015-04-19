<%-- 
    Document   : destroySession
    Created on : 18-Apr-2015, 11:49:45 PM
    Author     : c0647456
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session.invalidate();
    response.sendRedirect("index.jsp");
    %>

