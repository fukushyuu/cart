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
      //String message = (String) request.getAttribute("message");
      ResultSet rs = (ResultSet) request.getAttribute("rs");
      if (rs.next()) {
    %>

    <form action="ControllerBillDetail?action=changeQuantity&pid=<%=rs.getString(1)%>&oid=<%=rs.getString(2)%>" method="post">
      <table border="0">
        <label><%=title%></label>
        <tbody>
          <tr>
            <td>Product ID</td>
            <td><input type="text" name="pid" value="<%=rs.getString(1)%>" disabled></td>

          </tr>
          <tr>
            <td>Order ID</td>
            <td><input type="text" name="oid" value="<%=rs.getString(2)%>" disabled></td>
          </tr>
          <tr>
            <td>Quantity</td>
            <td><input type="number" name="quantity" value="<%=rs.getInt(3)%>"></td>
          </tr>
          <tr>
            <td><input type="submit" value="Change" name="submit"/></td>
            <td><input type="reset" value="Reset" /></td>
          </tr>
        </tbody>
      </table>
    </form>
    <%}%>
  </body>
</html>
