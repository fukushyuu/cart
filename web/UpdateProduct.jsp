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
      ResultSet rsCate = (ResultSet) request.getAttribute("rsCate");
      String title = (String) request.getAttribute("title");
    %>
    <%if (rs.next()) {%>
    <form action="ControllerProduct?action=update&pid=<%=rs.getString(1)%>" method="post">
      <table border="0">
        <label><%=title%></label>
        <tbody>
          <tr>
            <td>Category</td>
            <td>
              <select name="cate">
                <%while (rsCate.next()) {
                    if (rsCate.getInt(3) == 1) {
                %>
                <option value="<%=rsCate.getString(1)%>" <%=(rsCate.getString(1).equals(rs.getString(8)) ? "selected" : "")%>><%=rsCate.getString(2)%></option>
                <%
                    }
                  }
                %>
              </select>
            </td>
          </tr>
          <tr>
            <td>Product ID</td>
            <td><input type="text" name="pid" value="<%=rs.getString(1)%>" disabled></td>
          </tr>
          <tr>
            <td>Product Name</td>
            <td><input type="text" name="pname" value="<%=rs.getString(2)%>"></td>
          </tr>
          <tr>
            <td>Quantity</td>
            <td><input type="number" name="quantity" value="<%=rs.getInt(3)%>"></td>
          </tr>
          <tr>
            <td>Price</td>
            <td><input type="number" name="price" value="<%=rs.getDouble(4)%>"></td>
          </tr>
          <tr>
            <td>Image</td>
            <td><input type="text" name="image" value="<%=rs.getString(5)%>"></td>
          </tr>
          <tr>
            <td>Description</td>
            <td><input type="text" name="desc" value="<%=rs.getString(6)%>"></td>
          </tr>
          <tr>
            <td>Status</td>
            <td>
              <input type="radio" name="status" value="1" <%=(rs.getInt(7) == 1 ? "checked" : "")%>>Enable
              <input type="radio" name="status" value="0" <%=(rs.getInt(7) == 0 ? "checked" : "")%>>Disable
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
