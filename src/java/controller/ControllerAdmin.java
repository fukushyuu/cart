/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOAdmin;
import model.DAOBill;
import model.DAOCustomer;
import model.DAOProduct;

/**
 *
 * @author Nhat Nam
 */
@WebServlet(name = "ControllerAdmin", urlPatterns = {"/ControllerAdmin"})
public class ControllerAdmin extends HttpServlet {

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
    try (PrintWriter out = response.getWriter()) {
      DAOAdmin dao = new DAOAdmin();
      HttpSession session = request.getSession();
      String action = request.getParameter("action");

      if (action == null) {
        action = "login";
      }

      if (action.equals("listAll")) {
        String title = "Admin list";
        ArrayList<Admin> admins = dao.getAdmins();

        request.setAttribute("admins", admins);
        request.setAttribute("title", title);

        RequestDispatcher disp = request.getRequestDispatcher("/ViewAdmin.jsp");

        disp.forward(request, response);
      } //end listAll

      if (action.equals("insert")) {
        String submit = request.getParameter("submit");
        String title = "Insert Admin";
        String message = "";

        request.setAttribute("title", title);
        request.setAttribute("message", message);

        if (submit == null) {

          RequestDispatcher disp = request.getRequestDispatcher("/InsertAdmin.jsp");

          disp.forward(request, response);
        } else {
          String username = request.getParameter("username");
          String password = request.getParameter("password");
          String rePassword = request.getParameter("rePassword");
          //check data
          if (dao.checkExistUsername(username)) {
            message = "Username already exist";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/InsertAdmin.jsp").forward(request, response);
          }
          if (password.length() < 8) {
            message = "password's length must be longer than 8";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/InsertAdmin.jsp").forward(request, response);
          }
          if (!password.equals(rePassword)) {
            message = "Incorrect repeat password";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/InsertAdmin.jsp").forward(request, response);
          }
          //convert
          //add data
          dao.insertAdmin(new Admin(username, password));
          response.sendRedirect("ControllerAdmin");

        }
      }// end insert

      if (action.equals("delete")) {
        String adminID = request.getParameter("aid");
        dao.getData("delete from admin where adminid='" + adminID + "'");
        response.sendRedirect("ControllerAdmin");
      }

      if (action.equals("changePassword")) {
        String submit = request.getParameter("submit");
        int adminID = Integer.parseInt(request.getParameter("aid"));
        Admin admin = dao.getAdmin(adminID);
        String title = "Change password";
        String message = "";

        request.setAttribute("admin", admin);
        request.setAttribute("title", title);
        request.setAttribute("message", message);

        if (submit == null) {
          request.getRequestDispatcher("/ChangePasswordAdmin.jsp").forward(request, response);
        } else {
          String newPassword = request.getParameter("newPassword");
          String rePassword = request.getParameter("rePassword");
          //check data
          if (!newPassword.equals(rePassword)) {
            message = "Incorrect repeat password";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/ChangePasswordAdmin.jsp").forward(request, response);
          }
          //convert
          //add data
          dao.changePassword(adminID, newPassword);
          response.sendRedirect("ControllerAdmin");
        }
      } //end changePassword

      if (action.equals("login")) {
        String submit = request.getParameter("login");
        if (session.getAttribute("admin") != null) {//logged in -> cusManager
          request.getRequestDispatcher("/ControllerAdmin?action=cusManager").forward(request, response);
        }
        if (submit == null) {
          request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
        } else {
          String username = request.getParameter("username");
          String password = request.getParameter("password");
          Admin admin = dao.checkLogin(username, password);
          if (admin == null) { //check user exist or not
            request.setAttribute("message", "username or password is incorrect");
            request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
          }
          int id = admin.getAdminID();
          Admin ad = new Admin(id, username, password);
          session.setAttribute("admin", ad);
          request.getRequestDispatcher("/ControllerAdmin?action=cusManager").forward(request, response);
        }
      } //end login

      if (action.equals("cusManager")) {
        if (session.getAttribute("admin") == null) {
          request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
        }
        request.setAttribute("action", action);
        DAOCustomer daoC = new DAOCustomer();

        // model
        String sql = "select * from customer";

        // some information
        String title = "Customer list";
        String name = request.getParameter("name");
        if (name != null) {
          sql += "\nwhere cname like '%" + name + "%'";
        }

        ResultSet rs = daoC.getData(sql);

        // data view
        request.setAttribute("title", title);
        request.setAttribute("result", rs);
        request.setAttribute("name", name);

        // select view
        request.getRequestDispatcher("/adminIndex.jsp").forward(request, response);
      }// end cusManager

      if (action.equals("proManager")) {
        if (session.getAttribute("admin") == null) {
          request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
        }
        request.setAttribute("action", action);
        DAOProduct daoP = new DAOProduct();
        // model
        String sql = "select pid, pname, quantity, price, image, description, product.status, cateName\n"
                + "from product join Category\n"
                + "on Product.cateID = Category.cateID";
        // some information
        String title = "Product list";
        String name = request.getParameter("name");
        String sort = request.getParameter("sort");

        if (name != null) {
          sql += "\nwhere pname like '%" + name + "%'";
        }
        if (sort != null) {
          if (sort.equals("1")) {
            sql += "\norder by price asc";
          }
          if (sort.equals("0")) {
            sql += "\norder by price desc";
          }
        }

        ResultSet rs = daoP.getData(sql);

        // data view
        request.setAttribute("title", title);
        request.setAttribute("result", rs);
        request.setAttribute("name", name);
        request.setAttribute("sort", sort);

        // select view
        request.getRequestDispatcher("/adminIndex.jsp").forward(request, response);
      }// end proManager

      if (action.equals("billManager")) {
        if (session.getAttribute("admin") == null) {
          request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
        }

        request.setAttribute("action", action);
        DAOBill daoB = new DAOBill();
        String sql = "select * from bill";

        String title = "Bill list";
        String name = request.getParameter("name");
        String group = request.getParameter("group");

        if (name != null) {
          sql += "\nwhere cname like '%" + name + "%'";
        }
        if (group != null) {
          if (group.equals("0")) {
            sql += "\norder by dateCreate desc";
          }
          if (group.equals("1")) {
            sql += "\norder by status";
          }
          if (group.equals("2")) {
            sql += "\norder by cid";
          }
        }
        ResultSet rs = daoB.getData(sql);

        request.setAttribute("title", title);
        request.setAttribute("rs", rs);
        request.setAttribute("name", name);
        request.setAttribute("group", group);

        request.getRequestDispatcher("/adminIndex.jsp").forward(request, response);
      }

      if (action.equals("logout")) {
        session.invalidate();
        response.sendRedirect("adminLogin.jsp");
      }// end billManager

    }
  }

  protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("/index.jsp").forward(request, response);
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
