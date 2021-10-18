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
      ResultSet rsCate = (ResultSet) request.getAttribute("rsCate");
      String title = (String) request.getAttribute("title");
    %>
    <h3><%=title%></h3>
    <form action="ControllerProduct?action=insert" method="post">
      <table border="0">
        <tbody>
          <tr>
            <td>Product ID</td>
            <td><input type="text" name="pid" value=""></td>
          </tr>
          <tr>
            <td>Product Name</td>
            <td><input type="text" name="pname" value=""></td>
          </tr>
          <tr>
            <td>Quantity</td>
            <td><input type="number" name="quantity" value=""></td>
          </tr>
          <tr>
            <td>Price</td>
            <td><input type="number" name="price" value=""></td>
          </tr>
          <tr>
            <td>Image</td>
            <td><input type="text" name="image" value=""></td>
          </tr>
          <tr>
            <td>Description</td>
            <td><input type="text" name="desc" value=""></td>
          </tr>
          <tr>
            <td>Category Name</td>
            <td>
              <select name="cateID">
                <%while (rsCate.next()) {
                    if (rsCate.getInt(3) == 1) {
                %>
                <option value="<%=rsCate.getString(1)%>"><%=rsCate.getString(2)%></option>
                <%
                    }
                  }
                %>
              </select>
            </td>
          </tr>
          <tr>
            <td><input type="submit" value="Insert" name="submit"/></td>
            <td><input type="reset" value="Reset" /></td>
          </tr>
        </tbody>
      </table>

    </form>  
  </body>
</html>
