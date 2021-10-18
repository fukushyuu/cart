/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Category;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOCategory;

/**
 *
 * @author Nhat Nam
 */
@WebServlet(name = "ControllerCategory", urlPatterns = {"/ControllerCategory"})
public class ControllerCategory extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    DAOCategory dao = new DAOCategory();
    try (PrintWriter out = response.getWriter()) {
      String action = request.getParameter("action");
      if (action == null) {
        action = "listAll";
      }
      if (action.equals("listAll")) {
        String sql = "select * from category";
        ResultSet rs = dao.getData(sql);
        String title = "Category List";

        request.setAttribute("title", title);
        request.setAttribute("rs", rs);

        request.getRequestDispatcher("/ViewCategory.jsp").forward(request, response);

      }

      if (action.equals("insert")) {
        String submit = request.getParameter("submit");
        if (submit == null) {

          String title = "Insert Category";
          request.setAttribute("title", title);

          request.getRequestDispatcher("/InsertCategory.jsp").forward(request, response);
        } else {
          String cateName = request.getParameter("cateName");

          dao.addCategory(new Category(cateName));

          response.sendRedirect("ControllerCategory");
        }
      }

      if (action.equals("update")) {
        String submit = request.getParameter("submit");
        int cateID = Integer.parseInt(request.getParameter("cateID"));
        ResultSet rs = dao.getData("select * from category where cateID='" + cateID + "'");
        String title = "Update Category";

        request.setAttribute("rs", rs);
        request.setAttribute("title", title);

        if (submit == null) {
          request.getRequestDispatcher("/UpdateCategory.jsp").forward(request, response);
        } else {
          //insert data
          String cateName = request.getParameter("cateName");
          int status = Integer.parseInt(request.getParameter("status"));

          //update
          dao.updateCategory(new Category(cateID, cateName, status));

          response.sendRedirect("ControllerCategory");
        }
      }
      if (action.equals("delete")) {
        int cateID = Integer.parseInt(request.getParameter("cateID"));
        dao.removeCategory(cateID);

        response.sendRedirect("ControllerCategory");

      }

      /* TODO output your page here. You may use following sample code. */
//      out.println("<!DOCTYPE html>");
//      out.println("<html>");
//      out.println("<head>");
//      out.println("<title>Servlet ControllerCategory</title>");      
//      out.println("</head>");
//      out.println("<body>");
//      out.println("<h1>Servlet ControllerCategory at " + request.getContextPath() + "</h1>");
//      out.println("</body>");
//      out.println("</html>");
    }
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
