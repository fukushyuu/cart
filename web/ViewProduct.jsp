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
    <p><a href="ControllerProduct?action=insert">Insert product</a></p>
    <%
      ResultSet rs = (ResultSet) request.getAttribute("result");
      String title = (String) request.getAttribute("title");
      String name = (String) request.getAttribute("name");
      String sort = (String) request.getAttribute("sort");
    %>
    <form action="ControllerAdmin?action=proManager" method="post">
      <table>
        <tbody>
          <tr>
            <td>Search by Name</td>
            <td><input type="text" name="name" value="<%=(name!=null?name:"")%>" /></td>
          </tr>
          <tr>
            <td>Sort by Price</td>
            <td>
              <input type="radio" name="sort" value="1" onchange="submit(this);" <%=((sort!=null&&sort.equals("1"))?"checked":"")%>/>Ascending
              <input type="radio" name="sort" value="0" onchange="submit(this);" <%=((sort!=null&&sort.equals("0"))?"checked":"")%>/>Descending
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
          <th>PID</th>
          <th>Product Name</th>
          <th>Quantity</th>
          <th>Price</th>
          <th>Image</th>
          <th>Description</th>
          <th>Status</th>
          <th>CateID</th>
          <th>Update</th>
          <th>Delete</th>
        </tr>
      </thead>
      <tbody>
        <%while (rs.next()) {%>
        <tr>
          <td><%=rs.getString(1)%></td>
          <td><%=rs.getString(2)%></td>
          <td><%=rs.getString(3)%></td>
          <td><%=rs.getDouble(4)%></td>
          <td><img src="${pageContext.request.contextPath}/<%=rs.getString(5)%>" width="100px" height="100px"></td>
          <td><%=rs.getString(6)%></td>
          <td><%=(rs.getInt(7) == 1 ? "Enable" : "Disable")%></td>
          <td><%=rs.getString(8)%></td>
          <td><a href="ControllerProduct?action=update&pid=<%=rs.getString(1)%>">Update</a></td>
          <td><a href="ControllerProduct?action=delete&pid=<%=rs.getString(1)%>">Delete</a></td>
        </tr>
        <%}%>

      </tbody>
    </table>
  </body>
</html>
