<%-- 
    Document   : AddCategory
    Created on : 18-Apr-2015, 8:16:12 PM
    Author     : c0647456
--%>

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
        <script>
            $(document).ready(function() {                
                   //$.getJSON('./ws/blog', getBlog);
                $('#send').click(function() {
                    $.ajax({
                        url: "./ws/category",
                        dataType: "json",
                        contentType: 'application/json; charset=UTF-8',
                        data: JSON.stringify({"name": $("#title").val()}),
                        method: "POST"                       
                    });
                });
            });            
        </script>
    </head>
    <body>
        <section class='container'>
            <div class="col-md-8">
                <h1>Add Category</h1>
                <div class="form-group">
                    <label for="title">Title:</label>                    
                    <input id="title" class="form-control"/>
                </div>
                <button id="send" class="btn btn-default">Add</button>                
                <button id="update" class="btn btn-default invisible">Edit</button>                
                <div id="blog"></div>
            </div>
            <div class="col-md-4" id="catdiv"></div>            
        </section>
    </body>
</html>
