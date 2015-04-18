<%-- 
    Document   : index
    Created on : 18-Apr-2015, 3:12:06 PM
    Author     : c0647456
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap -->
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
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
        <script>
            $(document).ready(function() {
                $.getJSON("./ws/homepost", function(datapost) {
                $.getJSON("./ws/home", function(data) {
                    for (var i = 0, len = data.length; i < len; i++) {
                        console.log(data[i].category);
                        $('#ftBody').append("<tr><td class='text-center'><i class='fa fa-question fa-2x text-primary'></i></td><td><h4><a href='./showThread.jsp?cid="+data[i].c_id+"'>"+data[i].category+"</a><br><small>Some description</small></h4></td><td class='text-center hidden-xs hidden-sm'><a href='#'>"+data[i].topics+"</a></td><td class='text-center hidden-xs hidden-sm'><a href='#'>"+datapost[i].posts+"</a></td><td class='hidden-xs hidden-sm'>by <a href='#'>John Doe</a><br><small><i class='fa fa-clock-o'></i> 3 months ago</small></td></tr> ");
                    }
                });
            });
        });
        </script>
    </head>
    <body>
        <div class="container" style="margin-top: 35px">
            <div class="page-header page-heading">
                <h1 class="pull-left">Forums</h1>
                <ol class="breadcrumb pull-right where-am-i">
                    <li><a href="#">Forums</a></li>
                    <li class="active">List of topics</li>
                </ol>
                <div class="clearfix"></div>
            </div>
            <p class="lead">This is the right place to discuss any ideas, critics, feature requests and all the ideas regarding our website. Please follow the forum rules and always check FAQ before posting to prevent duplicate posts.</p>
            <table class="table forum table-striped">
                <thead>
                    <tr>
                        <th class="cell-stat"></th>
                        <th>
                <h3>Important</h3>
                </th>
                <th class="cell-stat text-center hidden-xs hidden-sm">Topics</th>
                <th class="cell-stat text-center hidden-xs hidden-sm">Posts</th>
                <th class="cell-stat-2x hidden-xs hidden-sm">Last Post</th>
                </tr>
                </thead>
                <tbody id="ftBody">
                </tbody>
            </table>

        </div>
    </div>
</body>
</html>
