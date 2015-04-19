<%-- 
    Document   : showPost
    Created on : 18-Apr-2015, 9:22:26 PM
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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap -->
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <title>JSP Page</title>
        <style>
            .forum .forum-post-panel {
                margin-bottom: 1em;
            }

            .forum.media-list li img.avatar {
                display: block;
                margin: 0 auto;
            }

            .forum.media-list li .user-info {
                text-align: center;
                width: 8em;
            }

            @media (max-width: 760px) {
                .forum.media-list li .user-info {
                    float: none;
                    width: 100%;
                    margin-bottom: 1em;
                }
            }
        </style>
    </head>
    <body>
        <div class="container">
            <ul class="media-list forum">

                <%
                    Connection cn = credentials.dbConnection.getConnection();
                    int t_id = Integer.valueOf(request.getParameter("tid"));
                    String query = "select p.description, u.username from post p, login u where p.u_id = u.u_id and p.t_id = ?";
                    PreparedStatement ps = cn.prepareStatement(query);
                    ps.setInt(1, t_id);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {%>
                <!-- Forum Post -->

                <li class="media well">
                    <div class="pull-left user-info" href="#">
                        <img class="avatar img-circle img-thumbnail" src="http://snipplicious.com/images/guest.png"
                             width="64" alt="Generic placeholder image">
                        <strong><a href="user.html"><%= rs.getString("username")%></a></strong>
                        <small>Member</small>
                        <br>
                    </div>
                    <div class="media-body">
                        <p><%= rs.getString("description")%></p>
                    </div>
                </li>
                <%
                    }
                %>




            </ul>
        </div>
    </body>
</html>