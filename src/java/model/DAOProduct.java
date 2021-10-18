/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nhat Nam
 */
public class DAOProduct extends DBConnect {

  public int insertProduct(Product pro) {
    int n = 0;
    String sql = "insert into Product(pid,pname,quantity,price,image,description,status,cateID) "
            + "values('" + pro.getPid() + "','" + pro.getPname() + "'," + pro.getQuantity() + "," + pro.getPrice() + ",'"
            + pro.getImage() + "','" + pro.getDescription() + "'," + pro.getStatus() + "," + pro.getCateID() + ")";
    try {
      Statement state = conn.createStatement();
      n = state.executeUpdate(sql);
    } catch (SQLException ex) {
      Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
    }
    return n;
  }

  public int addProduct(Product pro) {
    int n = 0;
    String sql = "insert into Product(pid,pname,quantity,price,image,description,cateID) "
            + "values(?,?,?,?,?,?,?)";

    try {
      PreparedStatement pre = conn.prepareStatement(sql);
//            set parameter ?
//            pre.setDataType(index,value);
//            DataType is datatype of field (attribute of table)
//            index of position of ? start 1
//            value is value parameter (pro)
      pre.setString(1, pro.getPid());
      pre.setString(2, pro.getPname());
      pre.setInt(3, pro.getQuantity());
      pre.setDouble(4, pro.getPrice());
      pre.setString(5, pro.getImage());
      pre.setString(6, pro.getDescription());
      pre.setInt(7, pro.getCateID());
      n = pre.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
    }

    return n;
  }

  public int updateProduct(Product pro) {
    int n = 0;
    String sql = "update Product set pname=?,quantity=?,price=?,image=?,description=?,status=?,cateID=? where pid=?";
    try {
      PreparedStatement pre = conn.prepareStatement(sql);
      pre.setString(1, pro.getPname());
      pre.setInt(2, pro.getQuantity());
      pre.setDouble(3, pro.getPrice());
      pre.setString(4, pro.getImage());
      pre.setString(5, pro.getDescription());
      pre.setInt(6, pro.getStatus());
      pre.setInt(7, pro.getCateID());
      pre.setString(8, pro.getPid());
      n = pre.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
    }
    return n;
  }

  public void updateQuantity(String id, int quan) {
    int n = 0;
    String sql = "update product set quantity=quantity+? where pid=?";

    try {
      PreparedStatement pre = conn.prepareStatement(sql);
      pre.setInt(1, quan);
      pre.setString(2, id);
      n = pre.executeUpdate();

    } catch (SQLException ex) {
      Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
    }
    if (n > 0) {
      System.out.println("successfuly");
    }
  }

  public void setQuantity(String id, int quan) {
    int n = 0;
    String sql = "update product set quantity=? where pid=?";

    try {
      PreparedStatement pre = conn.prepareStatement(sql);
      pre.setInt(1, quan);
      pre.setString(2, id);
      n = pre.executeUpdate();

    } catch (SQLException ex) {
      Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
    }
    if (n > 0) {
      System.out.println("successfuly");
    }
  }

  public void changeStatus(String id, int status) {
    int n = 0;
    String sql = "update product set status =? where pid =?";
    try {
      PreparedStatement pre = conn.prepareStatement(sql);
      pre.setInt(1, status);
      pre.setString(2, id);
      n = pre.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
    }
    if (n > 0) {
      System.out.println("successfuly");
    }
  }

  public int removeProduct(String id) {
    int n = 0;
    //check foreign key
    String checkFKey = "select * from billdetail where pid ='" + id + "'";
    ResultSet rs = getData(checkFKey);
    String sql = null;
    try {
      if (rs.next()) {
        sql = "update product set status=0 where pid ='" + id + "'";
      } else {
        sql = "delete from product where pid='" + id + "'";
      }
      Statement state = conn.createStatement();
      n = state.executeUpdate(sql);
    } catch (SQLException ex) {
      Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
    }
    return n;
  }

  public void displayAll() {
    String sql = "select * from product";
    try {
//      Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//      ResultSet rs = state.executeQuery(sql);
      ResultSet rs = getData(sql); // viet san ham trong DBConnect de tai su dung
      while (rs.next()) {
        String pid = rs.getString("pid");
        String pname = rs.getString(2);
        int quantity = rs.getInt(3);
        double price = rs.getDouble(4);
        String image = rs.getString("image");
        String description = rs.getString(6);
        int status = rs.getInt("status");
        int cateID = rs.getInt("cateid");
        Product pro = new Product(pid, pname, quantity, price, image, description, status, cateID);
        System.out.println(pro);
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public ArrayList<Product> getProduct() {
    ArrayList<Product> arr = new ArrayList<Product>();
    String sql = "select * from product";
    ResultSet rs = getData(sql);
    try {
      while (rs.next()) {
        String pid = rs.getString("pid");
        String pname = rs.getString(2);
        int quantity = rs.getInt(3);
        double price = rs.getDouble(4);
        String image = rs.getString("image");
        String description = rs.getString(6);
        int status = rs.getInt("status");
        int cateID = rs.getInt("cateid");
        Product pro = new Product(pid, pname, quantity, price, image, description, status, cateID);
        arr.add(pro);
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
    }
    return arr;
  }

  public Product getProduct(String pid) {
    String sql = "select * from product where pid='" + pid + "'";
    ResultSet rs = getData(sql);
    try {
      if (rs.next()) {
        String pname = rs.getString(2);
        int quantity = rs.getInt(3);
        double price = rs.getDouble(4);
        String image = rs.getString("image");
        String description = rs.getString(6);
        int status = rs.getInt("status");
        int cateID = rs.getInt("cateid");
        Product pro = new Product(pid, pname, quantity, price, image, description, status, cateID);
        return pro;
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  public ResultSet getPName(String name) {
    String sql = "select * from product join category on Product.cateID=Category.cateID where pname like '%" + name + "%'";
    return getData(sql);
  }

  public ResultSet getPrice(double from, double to) {
    String sql = "select * from product join category on Product.cateID=Category.cateID where price between " + from + " and " + to;
    return getData(sql);
  }

  public int getQuantity(String id) {
    String sql = "select quantity from product where pid='" + id + "'";
    ResultSet rs = getData(sql);
    try {
      if (rs.next()) {
        return rs.getInt(1);
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
    }
    return -1;
  }

  public void showInfo(String id) {
    String sql = "select * from product where pid='" + id + "'";
    ResultSet rs = getData(sql);
    try {
      if (rs.next()) {
        //String pid = rs.getString("pid");
        String pname = rs.getString(2);
        int quantity = rs.getInt(3);
        double price = rs.getDouble(4);
        String image = rs.getString("image");
        String description = rs.getString(6);
        int status = rs.getInt("status");
        int cateID = rs.getInt("cateid");
        Product pro = new Product(id, pname, quantity, price, image, description, status, cateID);
        System.out.println(pro);
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static void main(String[] args) {
    DAOProduct dao = new DAOProduct();
//    int n = dao.addProduct(new Product("P04", "HP G7", 3, 600, "no image", "second hand", 1));
//    dao.addProduct(new Product("L07", "Lenovo 12", 3, 600, "no image", "second hand", 1));
//    dao.addProduct(new Product("P15", "HP P5", 3, 800, "no image", "new style", 1));
//    dao.addProduct(new Product("A01", "Macbook", 3, 1200, "no image", "new style", 1));
//    dao.addProduct(new Product("M12", "Asus M12", 3, 600, "no image", "second hand", 1));
//    int n = dao.updateProduct(new Product("P04", "HP G7", 3, 600, "no image", "new style", 1, 1));
//    int n = dao.insertProduct(new Product("P04", "HP G7", 3, 600, "no image", "second hand", 1, 1));
//    if (n > 0) {
//      System.out.println("added");
//    }
    //dao.updateQuantity("P04", 5);
    //dao.changeStatus("P04", 0);
    //dao.removeProduct("P04");
    //dao.displayAll();
//    ResultSet rs = dao.getPrice(800, 2000);
//    try {
//      while (rs.next()) {
//        String pid = rs.getString("pid");
//        String pname = rs.getString(2);
//        int quantity = rs.getInt(3);
//        double price = rs.getDouble(4);
//        String image = rs.getString("image");
//        String description = rs.getString(6);
//        int status = rs.getInt("status");
//        int cateID = rs.getInt("cateid");
//        Product pro = new Product(pid, pname, quantity, price, image, description, status, cateID);
//        System.out.println(pro);
//      }
//    } catch (SQLException ex) {
//      Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    System.out.println(dao.getQuantity("a01"));
    System.out.println(dao.getProduct("A01"));;
  }
}
