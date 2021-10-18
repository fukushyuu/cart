/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Bill;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOBill;

/**
 *
 * @author Nhat Nam
 */
@WebServlet(name = "ControllerBill", urlPatterns = {"/ControllerBill"})
public class ControllerBill extends HttpServlet {

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
    DAOBill dao = new DAOBill();
    try (PrintWriter out = response.getWriter()) {
      /* TODO output your page here. You may use following sample code. */
      String action = request.getParameter("action");
      if (action == null) {
        action = "listAll";
      }
      if (action.equals("listAll")) {
        String sql = "select * from bill";
        ResultSet rs = dao.getData(sql);
        String title = "Bill list";
        
        request.setAttribute("rs", rs);
        request.setAttribute("title", title);
        
        request.getRequestDispatcher("/ViewBill.jsp").forward(request, response);

      } //end listAll

      if (action.equals("update")) {
        String submit = request.getParameter("submit");
        String oid = request.getParameter("oid");
        ResultSet rs = dao.getData("select * from bill where oid='" + oid + "'");
        String title="Update Bill";
        
        request.setAttribute("rs", rs);
        request.setAttribute("title", title);
        if (submit == null) {
          
          request.getRequestDispatcher("/UpdateBill.jsp").forward(request, response);
        } else {
          //insert data
          String name = request.getParameter("cname");
          String phone = request.getParameter("cphone");
          String address = request.getParameter("cadd");
          int status=Integer.parseInt(request.getParameter("status"));

          //convert data
          //update
          dao.changeInfo(new Bill(oid, name, phone, address, status));

          response.sendRedirect("ControllerAdmin?action=billManager");
        }
      }
//      if (action.equals("add")) {
//        String submit = request.getParameter("submit");
//        String oid = request.getParameter("oid");
//        ResultSet rs = dao.getData("select * from bill where oid='" + oid + "'");
//        String title="Update Bill";
//        
//        request.setAttribute("rs", rs);
//        request.setAttribute("title", title);
//        if (submit == null) {
//          
//          request.getRequestDispatcher("/UpdateBill.jsp").forward(request, response);
//        } else {
//          //insert data
//          String name = request.getParameter("cname");
//          String phone = request.getParameter("cphone");
//          String address = request.getParameter("cadd");
//          int status=Integer.parseInt(request.getParameter("status"));
//
//          //convert data
//          //update
//          dao.changeInfo(new Bill(oid, name, phone, address, status));
//
//          response.sendRedirect("ControllerBill");
//        }
//      }
//      if (action.equals("changeInfo")) {
//        String submit = request.getParameter("submit");
//        String oid = request.getParameter("oid");
//        ResultSet rsUpdate = dao.getData("select * from bill where oid='" + oid + "'");
//        if (submit == null) {
//          if (rsUpdate.next()) {
//
//            out.println("<form action=\"ControllerBill?action=changeInfo&oid=" + oid + "\" method=\"post\">\n"
//                    + "      <table>\n"
//                    + "        <tbody>\n"
//                    + "          <tr>\n"
//                    + "            <td>Order ID</td>\n"
//                    + "            <td><input type=\"text\" name=\"oid\" value=\"" + rsUpdate.getString(1) + "\" disabled/></td>\n"
//                    + "          </tr>\n"
//                    + "          <tr>\n"
//                    + "            <td>Customer name</td>\n"
//                    + "            <td><input type=\"text\" name=\"name\" value=\"" + rsUpdate.getString(3) + "\" /></td>\n"
//                    + "          </tr>\n"
//                    + "          <tr>\n"
//                    + "            <td>Customer phone</td>\n"
//                    + "            <td><input type=\"text\" name=\"phone\" value=\"" + rsUpdate.getString(4) + "\" /></td>\n"
//                    + "          </tr>\n"
//                    + "          <tr>\n"
//                    + "            <td>Customer address</td>\n"
//                    + "            <td><input type=\"text\" name=\"address\" value=\"" + rsUpdate.getString(5) + "\" /></td>\n"
//                    + "          </tr>\n"
//                    + "          <tr>\n"
//                    + "            <td>\n"
//                    + "              <input type=\"submit\" value=\"Update\" name=\"submit\" />\n"
//                    + "            </td>\n"
//                    + "            <td>\n"
//                    + "              <input type=\"reset\" value=\"Reset\" />\n"
//                    + "            </td>\n"
//                    + "          </tr>\n"
//                    + "        </tbody>\n"
//                    + "      </table>\n"
//                    + "    </form>");
//          }
//        } else {
//          //insert data
//          String name = request.getParameter("name");
//          String phone = request.getParameter("phone");
//          String address = request.getParameter("address");
//
//          //convert data
//          //update
//          dao.changeInfo(new Bill(oid, name, phone, address, 0, 0));
//
//          response.sendRedirect("ControllerBill");
//        }
//      }
//      out.println("<!DOCTYPE html>");
//      out.println("<html>");
//      out.println("<head>");
//      out.println("<title>Servlet ControllerBill</title>");      
//      out.println("</head>");
//      out.println("<body>");
//      out.println("<h1>Servlet ControllerBill at " + request.getContextPath() + "</h1>");
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
