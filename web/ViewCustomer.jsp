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
    <p><a href="ControllerCustomer?action=insert">Insert customer</a></p>
    <%
      ResultSet rs = (ResultSet) request.getAttribute("result");
      String title = (String) request.getAttribute("title");
      String name = (String) request.getAttribute("name");
    %>
    <form action="ControllerAdmin?action=cusManager" method="post">
      <table>
        <tbody>
          <tr>
            <td>Search by Name</td>
            <td><input type="text" name="name" value="<%=(name != null ? name : "")%>" /></td>
          </tr>
          <tr>
            <td>
              <input type="submit" value="Search" name="search" />
            </td>
            <td>
              <input type="reset" value="Reset" />
            </td>
          </tr>
        </tbody>
      </table>
    </form>
    <table border="1">
      <caption><%=title%></caption>
      <thead>
        <tr>
          <th>Customer ID</th>
          <th>Customer name</th>
          <th>Customer phone</th>
          <th>Customer address</th>
          <th>Username</th>
          <th>Password</th>
          <th>Status</th>
          <th>Update</th>
          <th>Delete</th>
          <th>Change Password</th>
        </tr>
      </thead>
      <tbody>
        <%while (rs.next()) {%>
        <tr>
          <td><%=rs.getString(1)%></td>
          <td><%=rs.getString(2)%></td>
          <td><%=rs.getString(3)%></td>
          <td><%=rs.getString(4)%></td>
          <td><%=rs.getString(5)%></td>
          <td><%=rs.getString(6)%></td>
          <td><%=(rs.getInt(7) == 1 ? "Enable" : "Disable")%></td>
          <td><a href="ControllerCustomer?action=update&cid=<%=rs.getString(1)%>">Update</a></td>
          <td><a href="ControllerCustomer?action=delete&cid=<%=rs.getString(1)%>">Delete</a></td>
          <td><a href="ControllerCustomer?action=changePassword&cid=<%=rs.getString(1)%>">Change</a></td>
        </tr>
        <%}%>

      </tbody>
    </table>
  </body>
</html>
