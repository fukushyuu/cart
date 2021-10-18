<%-- 
    Document   : ViewProduct
    Created on : Oct 6, 2021, 4:12:58 PM
    Author     : Nhat Nam
--%>
<%@page import="entity.Customer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  </head>
  <body>
    <%
      ResultSet rsCate = (ResultSet) request.getAttribute("reCate");
      String cateID = (String) request.getAttribute("cateID");
      if (cateID == null) {
        cateID = "";
      }
    %>
    <div class="left">
      <h3 style="text-align: center">Category</h3>
      <form action="cart" method="post">
        <div id="cateList" class="category-list">
          <a href="cart?cateID">All</a>
          <%                while (rsCate.next()) {
              if (rsCate.getInt("status") == 1) {
          %>
          <a href="cart?cateID=<%=rsCate.getString(1)%>"><%=rsCate.getString(2)%></a>
          <%
              }
            }
          %>
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
  </body>
</html>
