/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Admin;
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
public class DAOAdmin extends DBConnect {

  public int insertAdmin(Admin ad) {
    int n = 0;
    String sql = "insert into Admin(username,password) values('" + ad.getUsername() + "','" + ad.getPassword() + "')";
    try {
      Statement state = conn.createStatement();
      System.out.println(sql);
      n = state.executeUpdate(sql);

    } catch (SQLException ex) {
      Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
    }
    return n;
  }

  public void changePassword(int adminID, String password) {
    int n = 0;
    String sql = "update admin set password = ? where adminID=?";
    try {
      PreparedStatement pre = conn.prepareStatement(sql);
      pre.setString(1, password);
      pre.setInt(2, adminID);
      n = pre.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
    }
    if (n > 0) {
      System.out.println("change password successfuly");
    }
  }

  public Admin checkLogin(String username, String password) {
    Admin admin = null;
    String sql = "select * from admin where username=? and password=?";
    try {
      PreparedStatement pre = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      pre.setString(1, username);
      pre.setString(2, password);
      ResultSet rs = pre.executeQuery();
      if (rs.first()) {
        int adminID = rs.getInt("adminID");
        admin = new Admin(adminID, username, password);
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
    }
    return admin;
  }

  public void displayAll() {
    String sql = "select * from admin";
    ResultSet rs = getData(sql);
    try {
      while (rs.next()) {
        int id = rs.getInt(1);
        String username = rs.getString(2);
        String password = rs.getString(3);
        Admin ad = new Admin(id, username, password);
        System.out.println(ad);
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public ArrayList<Admin> getAdmins() {
    ArrayList<Admin> arr = new ArrayList<>();
    String sql = "select * from admin";
    ResultSet rs = getData(sql);
    try {
      while (rs.next()) {
        int id = rs.getInt(1);
        String username = rs.getString(2);
        String password = rs.getString(3);
        Admin ad = new Admin(id, username, password);
        arr.add(ad);
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
    }
    return arr;
  }

  public Admin getAdmin(int adminid) {
    Admin admin = null;
    String sql = "select * from admin WHERE adminid=" + adminid;
    ResultSet rs = getData(sql);
    try {
      while (rs.next()) {
        String username = rs.getString(2);
        String password = rs.getString(3);
        admin = new Admin(adminid, username, password);
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
    }
    return admin;
  }

  public boolean checkExistUsername(String username) {
    String sql = "SELECT * FROM admin WHERE username='" + username + "'";
    ResultSet rs = getData(sql);
    try {
      if (rs.first()) {
        return true;
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }

  public ResultSet getUsername(String name) {
    String sql = "select * from admin where username like '%" + name + "%'";
    return getData(sql);
  }

  public void showInfo(int id) {
    String sql = "select * from admin where adminid =" + id;
    ResultSet rs = getData(sql);
    try {
      if (rs.next()) {
        String username = rs.getString(2);
        String password = rs.getString(3);
        Admin ad = new Admin(id, username, password);
        System.out.println(ad);
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
