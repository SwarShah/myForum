<%-- 
    Document   : AdminPage
    Created on : 18-Apr-2015, 7:19:21 PM
    Author     : c0647456
--%>
<%
    try {
        String loggedIn = session.getAttribute("type").toString();
        if (loggedIn.equals("0")) {
            response.sendRedirect("index.jsp");
        }
    } catch (Exception e) {

    }

%>   
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    </head>
    <body>
        <section class='container'>
            <a href="./AddCategory.jsp">Add Category</a>
            
        </section>
    </body>
</html>
