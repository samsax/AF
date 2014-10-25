<%-- 
    Document   : message
    Created on : 6/10/2014, 03:53:49 PM
    Author     : samuel.arenas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Message</title>
    </head>
    <body>
         <center>
        <h3><%=request.getAttribute("Message")%></h3>
    </center>

    </body>
</html>
