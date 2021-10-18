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
    <%
      ResultSet rs = (ResultSet) request.getAttribute("rs");
      String title = (String) request.getAttribute("title");
      String name = (String) request.getAttribute("name");
      String group = (String) request.getAttribute("group");
    %>
    <form action="ControllerAdmin?action=billManager" method="post">
      <table>
        <tbody>
          <tr>
            <td>Search by Name</td>
            <td><input type="text" name="name" value="<%=(name != null ? name : "")%>" /></td>
          </tr>
          <tr>
            <td>Group by</td>
            <td>
              <input type="radio" name="group" value="0" onchange="submit(this);" <%=((group != null && group.equals("0")) ? "checked" : "")%>/>Date
              <input type="radio" name="group" value="1" onchange="submit(this);" <%=((group != null && group.equals("1")) ? "checked" : "")%>/>Status
              <input type="radio" name="group" value="2" onchange="submit(this);" <%=((group != null && group.equals("2")) ? "checked" : "")%>/>Customer ID
            </td>
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
          <th>Order ID</th>
          <th>Date create</th>
          <th>Customer name</th>
          <th>Customer phone</th>
          <th>Customer address</th>
          <th>Total</th>
          <th>Status</th>
          <th>Customer ID</th>
          <th>Order detail</th>
          <th>Update</th>
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
          <td><%=rs.getDouble(6)%></td>
          <td><%=(rs.getInt(7) == 0 ? "Waiting" : (rs.getInt(7) == 1 ? "Process" : "Done"))%></td>
          <td><%=rs.getString(8)%></td>
          <td><a href="ControllerBillDetail?action=detail&oid=<%=rs.getString(1)%>">Detail</a></td>
          <td><a href="ControllerBill?action=update&oid=<%=rs.getString(1)%>">Update</a></td>
        </tr>
        <%}%>

      </tbody>
    </table>
  </body>
</html>
