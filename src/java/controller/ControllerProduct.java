/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOProduct;

/**
 *
 * @author Nhat Nam
 */
@WebServlet(name = "ControllerProduct", urlPatterns = {"/ControllerProduct"})
public class ControllerProduct extends HttpServlet {

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
      
      DAOProduct dao = new DAOProduct();
      
      String action = request.getParameter("action");
      if (action == null) {
        action = "listAll";
      }
      if (action.equals("listAll")) {
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
        
        ResultSet rs = dao.getData(sql);

        // data view
        request.setAttribute("title", title);
        request.setAttribute("result", rs);
        request.setAttribute("name", name);
        request.setAttribute("sort", sort);

        // select view
        RequestDispatcher disp = request.getRequestDispatcher("/ViewProduct.jsp");
        // run
        disp.forward(request, response);
        
      }
      if (action.equals("insert")) {
        String submit = request.getParameter("submit");
        if (submit == null) {
          
          ResultSet rsCate = dao.getData("select * from category");
          
          String title = "Insert product";
          
          request.setAttribute("rsCate", rsCate);
          request.setAttribute("title", title);
          
          RequestDispatcher disp = request.getRequestDispatcher("/InsertProduct.jsp");
          
          disp.forward(request, response);
        } else {
          String pid = request.getParameter("pid");
          String pname = request.getParameter("pname");
          String quantity = request.getParameter("quantity");
          String price = request.getParameter("price");
          String image = request.getParameter("image");
          String desc = request.getParameter("desc");
          String cateID = request.getParameter("cateID");

          //check data
          //convert
          int intQuan = Integer.parseInt(quantity);
          double douPrice = Double.parseDouble(price);
          int intCate = Integer.parseInt(cateID);

          //add product
          dao.addProduct(new Product(pid, pname, intQuan, douPrice, image, desc, intCate));
          
          response.sendRedirect("ControllerAdmin?action=proManager");
        }
        
      }
      if (action.equals("update")) {
        String submit = request.getParameter("submit");
        String pid = request.getParameter("pid");
        if (submit == null) {
          
          ResultSet rs = dao.getData("select * from product where pid='" + pid + "'");
          ResultSet rsCate = dao.getData("select * from category");
          String title = "Update product";
          
          request.setAttribute("rs", rs);
          request.setAttribute("rsCate", rsCate);
          request.setAttribute("title", title);
          
          RequestDispatcher disp = request.getRequestDispatcher("/UpdateProduct.jsp");
          
          disp.forward(request, response);
        } else {
          //    String pid = request.getParameter("pid");
          String pname = request.getParameter("pname");
          String quantity = request.getParameter("quantity");
          String price = request.getParameter("price");
          String image = request.getParameter("image");
          String desc = request.getParameter("desc");
          String status = request.getParameter("status");
          String cateID = request.getParameter("cate");

          //check data
          //convert
          int intQuan = Integer.parseInt(quantity);
          double douPrice = Double.parseDouble(price);
          int intSta = Integer.parseInt(status);
          int intCate = Integer.parseInt(cateID);

          //add product
          dao.updateProduct(new Product(pid, pname, intQuan, douPrice, image, desc, intSta, intCate));
          
          response.sendRedirect("ControllerAdmin?action=proManager");
        }
      }
      if (action.equals("delete")) {
        String pid = request.getParameter("pid");
        dao.removeProduct(pid);
        response.sendRedirect("ControllerAdmin?action=proManager");
      }
      if (action.equals("searchByName")) {
        
      }
      if (action.equals("sort")) {
        
      }
      /* TODO output your page here. You may use following sample code. */
//      out.println("<!DOCTYPE html>");
//      out.println("<html>");
//      out.println("<head>");
//      out.println("<title>Servlet ControllerProduct</title>");
//      out.println("</head>");
//      out.println("<body>");
//      out.println("<h1>Servlet ControllerProduct at " + request.getContextPath() + "</h1>");
//      out.println("</body>");
//      out.println("</html>");
    }
  }
  
  public void dispatch(HttpServletRequest req, HttpServletResponse res, String url) throws ServletException, IOException {
    RequestDispatcher disp = req.getRequestDispatcher(url);
    disp.forward(req, res);
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
