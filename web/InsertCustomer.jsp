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
    <%
      String title = (String) request.getAttribute("title");
    %>
    <h3><%=title%></h3>
    <form action="ControllerCustomer?action=insert" method="post">
      <input type="hidden" name="title" value="<%=title%>">
      <table border="0">
        <tbody>
          <tr>
            <td>Customer name</td>
            <td><input type="text" name="cname" value="" required></td>
          </tr>
          <tr>
            <td>Customer phone</td>
            <td><input type="text" name="cphone" value=""></td>
          </tr>
          <tr>
            <td>Customer address</td>
            <td><input type="text" name="cadd" value=""></td>
          </tr>
          <tr>
            <td>Username</td>
            <td><input type="text" name="username" value="" required><span style="color: red">${message_user}</span></td>
          </tr>
          <tr>
            <td>Password</td>
            <td><input type="password" name="password" value="" required><span style="color: red">${message_pass}</span></td>
          </tr>
          <tr>
            <td>Repeat Password</td>
            <td><input type="password" name="rePassword" value="" required><span style="color: red">${message_rePass}</span></td>
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
