<%-- 
    Document   : ViewProduct
    Created on : Oct 6, 2021, 4:12:58 PM
    Author     : Nhat Nam
--%>
<%@page import="entity.Customer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  </head>
  <body>
    <table border="0">
      <thead>
        <tr>
          <th>
            <h3>
              <%                java.util.Enumeration em = session.getAttributeNames();
                boolean check = true;
                while (em.hasMoreElements()) {
                  String key = em.nextElement().toString();
                  if (key.equals("user")) {
                    check = false;
                    break;
                  }
                }
                Customer cus = (Customer) session.getAttribute("user");

                if (!check) {
                  out.println("<h3>Welcome " + cus.getCname() + "! | <a href='cart?action=logout'>Logout</a></h3>");
                } else {
              %>
              <a href="cart?action=login">Login</a>
              <%}%>
            </h3>
          </th>
          <th><h3>| <a href="cart?action=register">Register</a></h3></th>
          <th><h3>| <a href="cart?action=showCart">Go to cart</a></h3></th>
        </tr>
      </thead>
    </table>
  </body>
</html>
