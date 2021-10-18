<%-- 
    Document   : UpdateProduct
    Created on : Oct 6, 2021, 4:20:26 PM
    Author     : Nhat Nam
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <title>JSP Page</title>
  </head>
  <body>
    <h3>Login</h3>
    <form action="ControllerAdmin?action=login" method="post">
      <table border="0">
        <tbody>
          <span style="color: red">${message}</span>
          <tr>
            <td>Username</td>
            <td><input type="text" name="username" value="nhatnam"></td>
          </tr>
          <tr>
            <td>Password</td>
            <td><input type="password" name="password" value="12341234"></td>
          </tr>
          <tr>
            <td><input type="submit" value="Login" name="login"/></td>
            <td><input type="reset" value="Reset" /></td>
          </tr>
        </tbody>
      </table>
    </form>  
    <%//}%>
  </body>
</html>
