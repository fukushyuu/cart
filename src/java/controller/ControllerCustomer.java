/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOCustomer;

/**
 *
 * @author Nhat Nam
 */
@WebServlet(name = "ControllerCustomer", urlPatterns = {"/ControllerCustomer"})
public class ControllerCustomer extends HttpServlet {

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
    DAOCustomer dao = new DAOCustomer();
    try (PrintWriter out = response.getWriter()) {
      String action = request.getParameter("action");

      if (action == null) {
        action = "listAll";
      }

      if (action.equals("listAll")) {
        // model
        ResultSet rs = dao.listAll();

        // some information
        String title = "Customer list";

        // data view
        request.setAttribute("title", title);
        request.setAttribute("result", rs);

        // select view
        request.getRequestDispatcher("/ViewCustomer.jsp").forward(request, response);
      } //end listAll

      if (action.equals("insert")) {
        String submit = request.getParameter("submit");
        String message = "";

        request.setAttribute("message", message);

        if (submit == null) {
          String title = (String) request.getAttribute("title");
          if (title == null) {
            title = "Insert Customer";
          }
          request.setAttribute("title", title);
          request.getRequestDispatcher("/InsertCustomer.jsp").forward(request, response);
        } else {
          String cname = request.getParameter("cname");
          String cphone = request.getParameter("cphone");
          String cadd = request.getParameter("cadd");
          String username = request.getParameter("username");
          String password = request.getParameter("password");
          String rePassword = request.getParameter("rePassword");

          if (dao.checkExistUsername(username)) {
            message = "Username already exist";
            request.setAttribute("message_user", message);
            request.getRequestDispatcher("/InsertCustomer.jsp").forward(request, response);
          } else if (password.length() < 8) {
            message = "password must be longer than 8";
            request.setAttribute("message_pass", message);
            request.getRequestDispatcher("/InsertCustomer.jsp").forward(request, response);
          } else if (!password.equals(rePassword)) {
            message = "Incorrect repeat password";
            request.setAttribute("message_rePass", message);
            request.getRequestDispatcher("/InsertCustomer.jsp").forward(request, response);
          } else {
            //convert

            //add product
            dao.addCustomer(new Customer(cname, cphone, cadd, username, password));
            String title = request.getParameter("title");
            if (title.equals("Register")) {
              response.sendRedirect("cart");
            }
            if (title.equals("Insert Customer")) {
              response.sendRedirect("ControllerAdmin?action=cusManager");
            }
            //response.sendRedirect("cart");
          }
        }
      } // end insert

      if (action.equals("update")) {
        String submit = request.getParameter("submit");
        int cid = Integer.parseInt(request.getParameter("cid"));
        Customer cus = dao.getCustomerByID(cid);
        String title = "Update customer";

        request.setAttribute("cus", cus);
        request.setAttribute("title", title);
        if (submit == null) {

          request.getRequestDispatcher("/UpdateCustomer.jsp").forward(request, response);
        } else {
          //String cid = request.getParameter("cid");
          String cname = request.getParameter("cname");
          String cphone = request.getParameter("cphone");
          String cadd = request.getParameter("cadd");
          String username = request.getParameter("username");
          int status = Integer.parseInt(request.getParameter("status"));

          //check data
          if (dao.checkExistUsername(username)) {
            String message = "username already exist";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/UpdateCustomer.jsp").forward(request, response);
          }

          //convert
          //add product
          dao.updateCustomer(new Customer(cid, cname, cphone, cadd, username, "", status));

          response.sendRedirect("ControllerAdmin?action=cusManager");
        }
      } // end update

      if (action.equals("delete")) {
        int cid = Integer.parseInt(request.getParameter("cid"));
        dao.removeCustomer(cid);
        response.sendRedirect("ControllerAdmin?action=cusManager");
      }

      if (action.equals("changePassword")) {
        String submit = request.getParameter("submit");
        int cid = Integer.parseInt(request.getParameter("cid"));
        Customer cus = dao.getCustomerByID(cid);
        String title = "Change password";
        String message = "";

        request.setAttribute("cus", cus);
        request.setAttribute("title", title);
        request.setAttribute("message", message);

        if (submit == null) {
          request.getRequestDispatcher("/ChangePasswordCustomer.jsp").forward(request, response);
        } else {
          String newPassword = request.getParameter("newPassword");
          String rePassword = request.getParameter("rePassword");
          //check data
          if (!newPassword.equals(rePassword)) {
            message = "Incorrect repeat password";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/ChangePasswordCustomer.jsp").forward(request, response);
          } else {
            //convert
            //add data
            dao.changePassword(cid, newPassword);
            response.sendRedirect("ControllerAdmin?action=cusManager");
          }
        }
      } // end changePassword

      /* TODO output your page here. You may use following sample code. */
//      out.println("<!DOCTYPE html>");
//      out.println("<html>");
//      out.println("<head>");
//      out.println("<title>Servlet ControllerCustomer</title>");      
//      out.println("</head>");
//      out.println("<body>");
//      out.println("<h1>Servlet ControllerCustomer at " + request.getContextPath() + "</h1>");
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
