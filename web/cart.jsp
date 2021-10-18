<%-- 
    Document   : ViewProduct
    Created on : Oct 6, 2021, 4:12:58 PM
    Author     : Nhat Nam
--%>
<%@page import="java.util.Enumeration"%>
<%@page import="entity.Product"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>" />
    <title>JSP Page</title>
  </head>
  <body>
    <h2>YOUR CART</h2>
    <table border="1">
      <thead>
        <tr>
          <th>Image</th>
          <th>Product Name</th>
          <th>Description</th>
          <th>Price</th>
          <th>Quantity</th>
          <th>Total</th>
          <th>Remove</th>
        </tr>
      </thead>
      <tbody>
      <form action="cart?action=updateQuantity" method="POST">

        <%
          Enumeration em = session.getAttributeNames();
          double total = 0.0;
          while (em.hasMoreElements()) {
            String pid = em.nextElement().toString();
            if (!pid.equals("user")&&!pid.equals("admin")) {
              Product pro = (Product) session.getAttribute(pid);
              total += (pro.getPrice() * pro.getQuantity());
        %>
        <tr>
          <td><img src='<%= pro.getImage()%>' width='100px' height='100px'></td>
          <td><%= pro.getPname()%></td>
          <td><%= pro.getDescription()%></td>
          <td><%= pro.getPrice()%></td>
          <td><input type="number" name="quantity<%=pid%>" value="<%= pro.getQuantity()%>" style="width: 50px"> </td>
          <td><%= pro.getPrice() * pro.getQuantity()%></td>
          <td><a class="button" href="cart?action=remove&pid=<%=pid%>">Remove</a></td>

        </tr>

        <%}
          }%>
        <tr>
          <td colspan="4"><input class="button" type="submit" value="UPDATE" name="update"/></td>
          <td colspan="1">TOTAL</td>
          <td colspan="1"><%=total%></td>
          <td colspan="1"><a class="button" href="cart?action=removeAll">Remove All</a></td>
        </tr>
      </form>
    </tbody>
    <tfoot>
      <tr>
        <td colspan="7"></br></td>
      </tr>
      <tr>
        <td colspan="4"><a class="button" href="cart">SHOPPING CONTINUE</a></td>
        <td colspan="3"><a class="button" href="cart?action=checkout">CHECK OUT</a></td>
      </tr>
    </tfoot>
  </table>
</body>
</html>
