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
      ResultSet rs = (ResultSet) request.getAttribute("rs");
      String title = (String) request.getAttribute("title");
    %>
    <%if (rs.next()) {%>
    <form action="ControllerCategory?action=update&cateID=<%=rs.getString(1)%>" method="post">
      <table border="0">
        <label><%=title%></label>
        <tbody>
          <tr>
            <td>Category ID</td>
            <td><input type="text" name="cateID" value="<%=rs.getString(1)%>" disabled></td>
          </tr>
          <tr>
            <td>Category Name</td>
            <td><input type="text" name="cateName" value="<%=rs.getString(2)%>"></td>
          </tr>
          <tr>
            <td>Status</td>
            <td>
              <input type="radio" name="status" value="1" <%=(rs.getInt(3) == 1 ? "checked" : "")%>>Enable
              <input type="radio" name="status" value="0" <%=(rs.getInt(3) == 0 ? "checked" : "")%>>Disable
            </td>
          </tr>
          <tr>
            <td><input type="submit" value="Update" name="submit"/></td>
            <td><input type="reset" value="Reset" /></td>
          </tr>
        </tbody>
      </table>
    </form>
    <%}%>

  </body>
</html>
