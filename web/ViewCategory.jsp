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
    <p><a href="ControllerCategory?action=insert">Insert Category</a></p>
    <%
      ResultSet rs = (ResultSet) request.getAttribute("rs");
      String title = (String) request.getAttribute("title");

    %>
    <table border="1">
      <caption><%=title%></caption>
      <thead>
        <tr>
          <th>Category ID</th>
          <th>Category Name</th>
          <th>Status</th>
          <th>Update</th>
          <th>Delete</th>
        </tr>
      </thead>
      <tbody>
        <%while (rs.next()) {%>
        <tr>
          <td><%=rs.getString(1)%></td>
          <td><%=rs.getString(2)%></td>
          <td><%=(rs.getInt(3) == 1 ? "Enable" : "Disable")%></td>
          <td><a href="ControllerCategory?action=update&cateID=<%=rs.getString(1)%>">Update</a></td>
          <td><a href="ControllerCategory?action=delete&cateID=<%=rs.getString(1)%>">Delete</a></td>
        </tr>
        <%}%>

      </tbody>
    </table>
  </body>
</html>
