/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOBillDetail;

/**
 *
 * @author Nhat Nam
 */
@WebServlet(name = "ControllerBillDetail", urlPatterns = {"/ControllerBillDetail"})
public class ControllerBillDetail extends HttpServlet {

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
    DAOBillDetail dao = new DAOBillDetail();

    try (PrintWriter out = response.getWriter()) {

      String action = request.getParameter("action");
      if (action == null) {
        action = "listAll";
      }
      if (action.equals("listAll")) {

      }
      if (action.equals("detail")) {

        String oid = request.getParameter("oid");
        String sql = "select * from billdetail where oid = '" + oid + "'";
        ResultSet rs = dao.getData(sql);
        String title = "Order detail";

        request.setAttribute("rs", rs);
        request.setAttribute("title", title);

        request.getRequestDispatcher("/ViewBillDetail.jsp").forward(request, response);

      }
      if (action.equals("changeQuantity")) {
        String submit = request.getParameter("submit");
        String pid = request.getParameter("pid");
        String oid = request.getParameter("oid");
        ResultSet rs = dao.getData("select * from billdetail where pid='" + pid + "' and oid='" + oid + "'");
        String title = "Change Quantity";

        request.setAttribute("rs", rs);
        request.setAttribute("title", title);
        if (submit == null) {
          request.getRequestDispatcher("/ChangeQuantityBillDetail.jsp").forward(request, response);

        } else {
          //insert data
          int quantity = Integer.parseInt(request.getParameter("quantity"));
          //String price = rsUpdate.getString(4);

          //convert data
          //double pri = Double.parseDouble(price);
          //update    
          dao.changeQuantity(pid, oid, quantity);

          // update total theo quantity
          //dao.updateTotal(pid, oid, quan, pri);
          response.sendRedirect("ControllerBillDetail?action=detail&oid="+oid);

        }
      }
      /* TODO output your page here. You may use following sample code. */
//      out.println("<!DOCTYPE html>");
//      out.println("<html>");
//      out.println("<head>");
//      out.println("<title>Servlet ControllerBillDetail</title>");      
//      out.println("</head>");
//      out.println("<body>");
//      out.println("<h1>Servlet ControllerBillDetail at " + request.getContextPath() + "</h1>");
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
