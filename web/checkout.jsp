<%-- 
    Document   : checkout
    Created on : Oct 12, 2021, 9:58:58 PM
    Author     : Nhat Nam
--%>

<%@page import="entity.Customer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
  </head>
  <body>
    <%
//      session.invalidate();
      String showFrom = (String) request.getAttribute("showForm");
      Customer cus = (Customer) session.getAttribute("user");
      if (showFrom.equals("show")) {
    %>
    <h3>Checkout</h3>
    <form action="cart?action=checkout" method="post">
      <table border="0">
        <tbody>
          <tr>
            <td>Username</td>
            <td><input type="text" name="username" value="<%=cus.getUsername()%>" disabled=""></td>
          </tr>
          <tr>
            <td>Customer name</td>
            <td><input type="text" name="cname" value="<%=cus.getCname()%>"></td>
          </tr>
          <tr>
            <td>Customer phone</td>
            <td><input type="text" name="cphone" value="<%=cus.getCphone()%>"></td>
          </tr>
          <tr>
            <td>Customer address</td>
            <td><input type="text" name="cadd" value="<%=cus.getcAddress()%>"></td>
          </tr>
          <tr>
            <td><input type="submit" value="Checkout" name="submit"/></td>
            <td><input type="reset" value="Reset" /></td>
          </tr>
        </tbody>
      </table>

    </form>  
    <%} else {%>
    <h1>Successful Shopping!</h1>
    <h3><a href="cart">New shopping</a></h3>
    <%}%>
  </body>
</html>
