<%-- 
    Document   : upload
    Created on : 6/10/2014, 03:53:22 PM
    Author     : samuel.arenas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload</title>
    </head>
    <body>
        <center>
        <h1>File Upload to Database </h1>
            <form method="post" action="SubirServlet" enctype="multipart/form-data">
            <table border="0">
                <tr>
                    <td>First Name: </td>
                    <td><input type="text" name="nombre" size="50"/></td>
                </tr>
                <tr>
                    <td>Last Name: </td>
                    <td><input type="text" name="apellido" size="50"/></td>
                </tr>
                <tr>
                    <td>Portrait Photo: </td>
         <td><input type="file" name="foto" size="50"/></td>
                </tr>
                <tr>
                    <td colspan="2">
           <input type="submit" value="Save">
                    </td>
                </tr>
            </table>
        </form>
    </center>
    </body>
</html>
