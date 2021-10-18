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
      ResultSet rs = (ResultSet) request.getAttribute("rs");
      String title = (String) request.getAttribute("title");
    %>
    <%if (rs.next()) {%>
    <form action="ControllerBill?action=update&oid=<%=rs.getString(1)%>" method="post">
      <table border="0">
        <label><%=title%></label>
        <tbody>
          <tr>
            <td>Order ID</td>
            <td><input type="text" name="oid" value="<%=rs.getString(1)%>" disabled></td>
          </tr>
          <tr>
            <td>Customer Name</td>
            <td><input type="text" name="cname" value="<%=rs.getString(3)%>"></td>
          </tr>
          <tr>
            <td>Customer Phone</td>
            <td><input type="text" name="cphone" value="<%=rs.getString(4)%>"></td>
          </tr>
          <tr>
            <td>Customer Address</td>
            <td><input type="text" name="cadd" value="<%=rs.getString(5)%>"></td>
          </tr>
          <tr>
            <td>Status</td>
            <td>
              <input type="radio" name="status" value="0" <%=(rs.getInt(7) == 0 ? "checked" : "")%>>Waiting
              <input type="radio" name="status" value="1" <%=(rs.getInt(7) == 1 ? "checked" : "")%>>Process
              <input type="radio" name="status" value="2" <%=(rs.getInt(7) == 2 ? "checked" : "")%>>Done
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
