<%-- 
    Document   : ViewProduct
    Created on : Oct 6, 2021, 4:12:58 PM
    Author     : Nhat Nam
--%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="entity.Admin"%>
<%@page import="entity.Customer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>" />

  </head>
  <body>
    <%
      DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
      String creationTime = formatter.format(session.getCreationTime());
      String lastAccessedTime = formatter.format(session.getLastAccessedTime());
    %>
    <span>Hello <%=((Admin) session.getAttribute("admin")).getUsername()%></span>
    <a href="ControllerAdmin?action=logout">Logout</a>
    <p>Creation Time: <%=creationTime%></p>
    <p>Last Accessed Time: <%=lastAccessedTime%></p>
    <p>Max Inactive Interval: <%=session.getMaxInactiveInterval()%></p>

    <div class="container">
      <div class="left">
        <form action="cart" method="post">
          <div id="cateList" class="category-list">
            <a href="ControllerAdmin?action=cusManager">Customer Manager</a>
            <a href="ControllerAdmin?action=proManager">Product Manager</a>
            <a href="ControllerAdmin?action=billManager">Bill Manager</a>

          </div>
          <script>
            var cateList = document.getElementById("cateList");
            var links = cateList.getElementsByTagName("a");
            for (var i = 0; i < links.length; i++) {
              links[i].addEventListener("click", function () {
                var current = document.getElementsByClassName("active");
                current[0].className = current.className.replace(" active", "");
                this.className += " active";
              })
            }
          </script>
        </form>
      </div>
      <div class="right">
        <%String action = request.getAttribute("action").toString();
          if (action == null || action.equals("cusManager")) {%>
        <%@include file="ViewCustomer.jsp" %>
        <%}
          if (action != null && action.equals("proManager")) {%>
        <%@include file="ViewProduct.jsp" %>
        <%}
          if (action != null && action.equals("billManager")) {%>
        <%@include file="ViewBill.jsp" %>
        <%}
        %>
      </div>
    </div>
  </body>
</html>
