<%-- 
    Document   : ViewProduct
    Created on : Oct 6, 2021, 4:12:58 PM
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
      ResultSet rs = (ResultSet) request.getAttribute("rs");
      String title = (String) request.getAttribute("title");

    %>
    <table border="1">
      <caption><%=title%></caption>
      <thead>
        <tr>
          <th>Product ID</th>
          <th>Order ID</th>
          <th>Quantity</th>
          <th>Price</th>
          <th>Total</th>
          <th>Change Quantity</th>
        </tr>
      </thead>
      <tbody>
        <%while (rs.next()) {%>
        <tr>
          <td><%=rs.getString(1)%></td>
          <td><%=rs.getString(2)%></td> 
          <td><%=rs.getString(3)%></td>
          <td><%=rs.getString(4)%></td>
          <td><%=rs.getString(5)%></td>
          <td><a href="ControllerBillDetail?action=changeQuantity&pid=<%=rs.getString(1)%>&oid=<%=rs.getString(2)%>">Change</a></td>
        </tr>
        <%}%>

      </tbody>
    </table>
  </body>
</html>
