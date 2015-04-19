<%-- 
    Document   : testjsp
    Created on : 18-Apr-2015, 7:37:19 PM
    Author     : c0647456
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <%
            Connection cn = credentials.dbConnection.getConnection();
            String query = "SELECT * FROM THREAD WHERE C_ID = ?";
            PreparedStatement ps = cn.prepareStatement(query);
            int id = Integer.valueOf(request.getParameter("cid"));            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){%>
            <h1><%= rs.getString("description") %></h1>                
        <%        
            }
        %>
        <h1>Hello World! <%= request.getParameter("name") %></h1>
        
    </body>
</html>
