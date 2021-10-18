<%-- 
    Document   : ViewProduct
    Created on : Oct 6, 2021, 4:12:58 PM
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
    <p><a href="ControllerAdmin?action=insert">Insert Admin</a></p>
    <table border="1">
      <caption>${title}</caption>
      <thead>
        <tr>
          <th>Admin ID</th>
          <th>Username</th>
          <th>Password</th>
          <th>Change password</th>
          <th>Delete</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="item" items="${admins}">
          <tr>
            <td>${item.adminID}</td>
            <td>${item.username}</td>
            <td>${item.password}</td>
            <td><a href="ControllerAdmin?action=changePassword&aid=${item.adminID}">Change</a></td>
            <td><a href="ControllerAdmin?action=delete&aid=${item.adminID}">Delete</a></td>
          </tr>
        </c:forEach>

      </tbody>
    </table>
  </body>
</html>
