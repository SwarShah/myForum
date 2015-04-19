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
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <style>
            .forum.table > tbody > tr > td {
                vertical-align: middle;
            }

            .forum .fa {
                width: 1em;
                text-align: center;
            }

            .forum.table th.cell-stat {
                width: 6em;
            }

            .forum.table th.cell-stat-2x {
                width: 14em;
            }
        </style>
    </head>
    <body>


        <div class="container" style="margin-top: 35px">
            <div class="page-header page-heading">
                <h1 class="pull-left">Forums</h1>
                <ol class="breadcrumb pull-right where-am-i">
                    <%
                        try {
                            String loggedIn = session.getAttribute("loggedIn").toString();
                            if (loggedIn.equals("true")) {
                    %>
                    <a href="destroySession.jsp">Logout</a>
                    <% } else {%>
                    <a href="login.jsp">Login</a>
                    <% }
                    } catch (Exception e) {%>
                    <a href="login.jsp">Login</a><%
                        }
                    %> 
                </ol>
                <div class="clearfix"></div>
            </div>
            <table class="table forum table-striped">
                <tbody id="ftBody">
                    <%
                        Connection cn = credentials.dbConnection.getConnection();
                        String query = "SELECT t.*, l.username FROM THREAD t, login l WHERE C_ID = ? AND t.u_id = l.u_id";
                        PreparedStatement ps = cn.prepareStatement(query);
                        int id = Integer.valueOf(request.getParameter("cid"));
                        ps.setInt(1, id);
                        ResultSet rs = ps.executeQuery();
                                while (rs.next()) {%>
                                <tr><td class='text-center'><i class='fa fa-comments fa-2x text-primary'></i></td><td><h4><a href='./showThread.jsp?cid=" + data[i].c_id + "'> <%= rs.getString("description")%> </a><br><small>Some description</small></h4></td><td class='text-center hidden-xs hidden-sm'><a href='#'></a></td><td class='text-center hidden-xs hidden-sm'><a href='#'></a></td><td class='hidden-xs hidden-sm'>by <a href='#'><%= rs.getString("username")%></a><br><small><i class='fa fa-clock-o'></i> 3 months ago</small></td></tr>
                             
                <%
                    }
                %>
                
                </tbody>
            </table>

        </div>

</body>
</html>