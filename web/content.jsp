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
    <%
      ResultSet rs = (ResultSet) request.getAttribute("result");
      String title = (String) request.getAttribute("title");
    %>
    <div class="right">
      <h3 style="text-align: center"><%=title%></h3>
      <table border="1">

        <thead>
          <tr>
            <th>Image</th>
            <th>Product Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Add to cart</th>
          </tr>
        </thead>
        <tbody>
          <%while (rs.next()) {%>
          <tr>
            <td><img src="<%=rs.getString(5)%>" width="100px" height="100px"></td>
            <td><%=rs.getString(2)%></td>
            <td><%=rs.getString(6)%></td>
            <td><%=rs.getDouble(4)%></td>
            <td>
              <a class="button"
                 type="button" 
                 href="cart?action=add&pid=<%=rs.getString(1)%>" 
                 >ADD</a>
            </td>
          </tr>
          <%}%>
        </tbody>
      </table>
    </div>
  </body>
</html>
