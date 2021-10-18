<%-- 
    Document   : UpdateProduct
    Created on : Oct 6, 2021, 4:20:26 PM
    Author     : Nhat Nam
--%>

<%@page import="entity.Customer"%>
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
      Customer cus = (Customer) request.getAttribute("cus");
      String title = (String) request.getAttribute("title");
    %>
    <form action="ControllerCustomer?action=update&cid=<%=cus.getCid()%>" method="post">
      <table border="0">
        <label><%=title%></label>
        <tbody>
          <tr>
            <td>Customer ID</td>
            <td><input type="text" name="cid" value="<%=cus.getCid()%>" disabled></td>
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
            <td>Username</td>
            <td><input type="text" name="username" value="<%=cus.getUsername()%>"><span>${message}</span></td>
          </tr>
          <tr>
            <td>Status</td>
            <td>
              <input type="radio" name="status" value="1" <%=(cus.getStatus() == 1 ? "checked" : "")%>>Enable
              <input type="radio" name="status" value="0" <%=(cus.getStatus() == 0 ? "checked" : "")%>>Disable
            </td>
          </tr>
          <tr>
            <td><input type="submit" value="Update" name="submit"/></td>
            <td><input type="reset" value="Reset" /></td>
          </tr>
        </tbody>
      </table>
    </form>

  </body>
</html>
