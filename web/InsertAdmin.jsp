<%-- 
    Document   : UpdateProduct
    Created on : Oct 6, 2021, 4:20:26 PM
    Author     : Nhat Nam
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
  </head>
  <body>
    <%@include file="index.html" %>
    <%
      String title = (String) request.getAttribute("title");
      String message = (String) request.getAttribute("message");
    %>
    <form action="ControllerAdmin?action=insert" method="post">
      <table border="0">
        <label><%=title%></label><br>
        <tbody>
        <label style="color: red"><%=message%></label>
        <tr>
          <td>Username</td>
          <td><input type="text" name="username" value=""></td>
        </tr>
        <tr>
          <td>Password</td>
          <td><input type="password" name="password" value=""></td>
        </tr>
        <tr>
          <td>Repeat Password</td>
          <td><input type="password" name="rePassword" value=""></td>
        </tr>
        <tr>
          <td><input type="submit" value="Insert" name="submit"/></td>
          <td><input type="reset" value="Reset" /></td>
        </tr>
        </tbody>
      </table>

    </form>  
  </body>
</html>
