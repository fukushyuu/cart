/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Category;
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
public class DAOCategory extends DBConnect { //Database Access Object
  //    DBConnect dbconn;
//    Connection conn;
//
//    public DAOCategory(DBConnect dbconn) {
//        this.dbconn = dbconn;
//        conn=dbconn.conn;
//    }

  public int addCategory(Category cate) {
    int n = 0;
    String sql = "insert into Category (cateName) values('" + cate.getCateName() + "')";
    System.out.println(sql);
    try {
      Statement state = conn.createStatement();
      n = state.executeUpdate(sql);
    } catch (SQLException ex) {
      Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
    }
    return n;
  }

  public int updateCategory(Category cate) {
    int n = 0;
    String sql = "update category set cateName=?, status=? where cateID=?";
    try {
      PreparedStatement pre = conn.prepareStatement(sql);
      pre.setString(1, cate.getCateName());
      pre.setInt(2, cate.getStatus());
      pre.setInt(3, cate.getCateID());
      n = pre.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
    }
    return n;
  }

  public int removeCategory(int cateID) {
    int n = 0;
    String checkFKey = "select * from product where cateID=" + cateID;
    ResultSet rs = getData(checkFKey);
    String sql = "";
    try {
      if (rs.next()) {
        sql = "update category set status=0 where cateID=" + cateID;
      } else {
        sql = "delete from category where cateID=" + cateID;
      }
      Statement state = conn.createStatement();
      n = state.executeUpdate(sql);
    } catch (SQLException ex) {
      Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
    }
    return n;
  }

  public void displayAll() {
    String sql = "select * from category";
    ResultSet rs = getData(sql);
    try {
      while (rs.next()) {
        int id = rs.getInt(1);
        String name = rs.getString(2);
        int status = rs.getInt(3);
        Category cate = new Category(id, name, status);
        System.out.println(cate);
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public ArrayList<Category> getCategory() {
    ArrayList<Category> arr = new ArrayList<>();
    String sql = "select * from category";
    ResultSet rs = getData(sql);
    try {
      while (rs.next()) {
        int id = rs.getInt(1);
        String name = rs.getString(2);
        int status = rs.getInt(3);
        Category cate = new Category(id, name, status);
        arr.add(cate);
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
    }
    return arr;
  }

  public ResultSet getName(String name) {
    String sql = "select * from category where cateName like '%" + name + "%'";
    return getData(sql);
  }

  public void showInfo(int cid) {
    String sql = "select * from category where cateid=" + cid;
    ResultSet rs = getData(sql);
    try {
      if (rs.next()) {
        String name = rs.getString(2);
        int status = rs.getInt(3);
        Category cate = new Category(cid, name, status);
        System.out.println(cate);
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static void main(String[] args) {
    DAOCategory dao = new DAOCategory();
    dao.removeCategory(11);
//    int n = dao.addCategory(new Category("apple"));
//    System.out.println(n);
//    if (n > 0) {
//      System.out.println("inserted");
//    }
//    dao.updateCategory(new Category(1, "Laptop", 1));
//    dao.displayAll();
  }
}
