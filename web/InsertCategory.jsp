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
    %>
    <form action="ControllerCategory?action=insert" method="post">
      <table border="0">
        <label><%=title%></label>
        <tbody>
          <tr>
            <td>Category Name</td>
            <td><input type="text" name="cateName" value=""></td>
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
