/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Bill;
import entity.BillDetail;
import entity.Customer;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOBill;
import model.DAOBillDetail;
import model.DAOCustomer;
import model.DAOProduct;

/**
 *
 * @author Nhat Nam
 */
public class ControllerCart extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  public ControllerCart() {
    super();
  }

  DAOProduct daoPro = new DAOProduct();
  DAOCustomer daoC = new DAOCustomer();
  DAOBill daoB = new DAOBill();
  DAOBillDetail daoBD = new DAOBillDetail();

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      String action = request.getParameter("action");
      HttpSession session = request.getSession();

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
        String cateID = request.getParameter("cateID");

        if (cateID != null && !cateID.equals("")) {
          sql += "\nwhere Product.cateID ='" + cateID + "'";
        }
        ResultSet rs = daoPro.getData(sql);
        ResultSet rsCate = daoPro.getData("select * from category");

        // data view
        request.setAttribute("title", title);
        request.setAttribute("result", rs);
        request.setAttribute("reCate", rsCate);
        request.setAttribute("cateID", cateID);

        // select view
        request.getRequestDispatcher("/listItem.jsp").forward(request, response);
      }

      if (action.equals("login")) {
        String login = request.getParameter("login");
        if (login == null) {
          request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
          String username = request.getParameter("username");
          String password = request.getParameter("password");
          String page = request.getParameter("page");

          Customer cus = daoC.checkLogin(username, password);
          if (cus != null) { //check user exist or not exist
            session.setAttribute("user", cus);
            if (page.equals("checkout")) { //login from checkout -> return checkout page
              request.getRequestDispatcher("/cart?action=checkout").forward(request, response);
            }
            response.sendRedirect("cart");
          } else {
            request.setAttribute("message", "username or password is incorrect");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
          }
        }
      }

      if (action.equals("logout")) {
        session.invalidate();
        response.sendRedirect("cart");
      }

      if (action.equals("register")) {
        request.setAttribute("title", "Register");
        request.getRequestDispatcher("/ControllerCustomer?action=insert").forward(request, response);
      }

      if (action.equals("add")) { //add to cart
        String pid = request.getParameter("pid");

        if (pid != null) {
          Product purchasePro = (Product) session.getAttribute(pid);

          if (purchasePro == null) {
            Product pro = daoPro.getProduct(pid);
            pro.setQuantity(1);
            session.setAttribute(pid, pro);
          } else {
            purchasePro.setQuantity(purchasePro.getQuantity() + 1);
          }
        }

        response.sendRedirect("cart?action=showCart");
      }

      if (action.equals("showCart")) {
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
      }

      if (action.equals("updateQuantity")) {
        Enumeration em = session.getAttributeNames();
        while (em.hasMoreElements()) {
          String pid = em.nextElement().toString();
          if (!pid.equals("user") && !pid.equals("admin")) {
            int quantity = Integer.parseInt(request.getParameter("quantity" + pid));
            Product pro = (Product) session.getAttribute(pid);
            pro.setQuantity(quantity);
          }
        }
        response.sendRedirect("cart?action=showCart");
      }

      if (action.equals("remove")) {
        String id = request.getParameter("pid");
        session.removeAttribute(id);
        response.sendRedirect("cart?action=showCart");
      }

      if (action.equals("removeAll")) {
        Enumeration em = session.getAttributeNames();
        while (em.hasMoreElements()) {
          String pid = em.nextElement().toString();
          if (!pid.equals("user") && !pid.equals("admin")) {
            session.removeAttribute(pid);
          }
        }
        response.sendRedirect("cart?action=showCart");
      }

      if (action.equals("checkout")) {
        String submit = request.getParameter("submit");
        if (session.getAttribute("user") == null) {
          request.setAttribute("page", "checkout");
          request.getRequestDispatcher("cart?action=login").forward(request, response);
        }
        if (submit == null) {
          request.setAttribute("showForm", "show");
          request.getRequestDispatcher("/checkout.jsp").forward(request, response);
        } else {
          request.setAttribute("showForm", "hiden");
          Enumeration em = session.getAttributeNames();
          String oid = generateOID();
          String cname = request.getParameter("cname");
          String cphone = request.getParameter("cphone");
          String cadd = request.getParameter("cadd");
          double total = 0.0;
          int cid = ((Customer) session.getAttribute("user")).getCid();
          while (em.hasMoreElements()) { //calculate total
            String pid = em.nextElement().toString();
            if (!pid.equals("user") && !pid.equals("admin")) {
              Product pro = (Product) session.getAttribute(pid);
              total += (pro.getQuantity() * pro.getPrice());
            }
          }
          // add bill
          daoB.addBill(new Bill(oid, cname, cphone, cadd, total, cid));
          em = session.getAttributeNames(); //back key to first
          while (em.hasMoreElements()) { //add bill detail and remove session
            String pid = em.nextElement().toString();
            if (!pid.equals("user") && !pid.equals("admin")) {
              Product pro = (Product) session.getAttribute(pid);
              daoBD.addBillDetail(new BillDetail(pid, oid, pro.getQuantity(), pro.getPrice(), pro.getQuantity() * pro.getPrice()));
              session.removeAttribute(pid);
            }
          }
          request.getRequestDispatcher("/checkout.jsp").forward(request, response);
        }
      }
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

  private String generateOID() {
    int id = (int) (Math.random() * 1000);
    String orID = "or" + id;
    ResultSet rs = daoB.getData("select * from bill");
    try {
      while (rs.next()) {
        String oid = rs.getString("oID");
        if (orID.equals(oid)) {
          generateOID();
        }
      }
    } catch (SQLException ex) {
      Logger.getLogger(ControllerCart.class.getName()).log(Level.SEVERE, null, ex);
    }
    return orID;
  }

//  public static void main(String[] args) {
//    ControllerCart cc = new ControllerCart();
//    DAOBillDetail daoBD = new DAOBillDetail();
//
//    daoBD.addBillDetail(new BillDetail(pid, oid, 1, 200, 1 * 200));
//
//  }
}
