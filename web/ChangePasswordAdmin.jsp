<%-- 
    Document   : UpdateProduct
    Created on : Oct 6, 2021, 4:20:26 PM
    Author     : Nhat Nam
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
    <form action="ControllerAdmin?action=changePassword&aid=${admin.adminID}" method="post">
      <table border="0">
        <label>${title}</label>
        <tbody>
          <tr>
            <td>Username</td>
            <td><input type="text" name="username" value="${admin.username}" disabled></td>

          </tr>
          <tr>
            <td>Password</td>
            <td><input type="password" name="newPassword" value=""></td>
          </tr>
          <tr>
            <td>Repeat Password</td>
            <td style="color: red"><input type="password" name="rePassword" value="">${message}</td>
          </tr>
          <tr>
            <td><input type="submit" value="Change" name="submit"/></td>
            <td><input type="reset" value="Reset" /></td>
          </tr>
        </tbody>
      </table>
    </form>
  </body>
</html>
