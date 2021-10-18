/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Customer;
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
public class DAOCustomer extends DBConnect {

  public int insertCustomer(Customer cus) {
    int n = 0;
    String sql = "insert into customer (cname ,cphone , cAddress , username ,password )"
            + " values('" + cus.getCname() + "','" + cus.getCphone() + "','"
            + cus.getcAddress() + "','" + cus.getUsername() + "','" + cus.getPassword() + "')";
    try {
      System.out.println(sql);
      Statement state = conn.createStatement();
      n = state.executeUpdate(sql);
    } catch (SQLException ex) {
      Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
    }

    return n;
  }

  public int addCustomer(Customer cus) {
    int n = 0;
    String sql = "insert into customer (cname ,cphone , cAddress , username ,password ) values (?,?,?,?,?)";
    try {
      PreparedStatement pre = conn.prepareStatement(sql);
      pre.setString(1, cus.getCname());
      pre.setString(2, cus.getCphone());
      pre.setString(3, cus.getcAddress());
      pre.setString(4, cus.getUsername());
      pre.setString(5, cus.getPassword());
      n = pre.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
    }
    return n;

  }

  public int updateCustomer(Customer cus) {
    int n = 0;
    String sql = "update Customer set cname=? ,cphone=? , cAddress=? , username=?, status=? where cid=?";
    try {
      PreparedStatement pre = conn.prepareStatement(sql);
      pre.setString(1, cus.getCname());
      pre.setString(2, cus.getCphone());
      pre.setString(3, cus.getcAddress());
      pre.setString(4, cus.getUsername());
      //pre.setString(5, cus.getPassword());
      pre.setInt(5, cus.getStatus());
      pre.setInt(6, cus.getCid());
      n = pre.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
    }
    return n;
  }

  public void changePassword(int cid, String password) {
    String sql = "update customer set password=? where cid=?";
    try {
      PreparedStatement pre = conn.prepareStatement(sql);
      pre.setString(1, password);
      pre.setInt(2, cid);
      pre.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void removeCustomer(int cid) {
    String checkFKey = "select * from bill where cid =" + cid;
    ResultSet rs = getData(checkFKey);
    String sql = null;
    try {
      if (rs.next()) {
        sql = "update customer set status=0 where cid=" + cid;
      } else {
        sql = "delete from customer where cid=" + cid;
      }
      Statement state = conn.createStatement();
      state.executeUpdate(sql);
    } catch (SQLException ex) {
      Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public ResultSet listAll() {
    String sql = "select * from customer";
    return getData(sql);
  }

  public void displayAll() {
    String sql = "select * from customer";
    ResultSet rs = getData(sql);
    try {
      while (rs.next()) {
        int id = rs.getInt(1);
        String name = rs.getString(2);
        String phone = rs.getString(3);
        String address = rs.getString(4);
        String username = rs.getString(5);
        String password = rs.getString(6);
        int status = rs.getInt(7);
        Customer cus = new Customer(id, name, phone, address, username, password, status);
        System.out.println(cus);
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public Customer getCustomerByID(int cid) {
    Customer cus = null;
    String sql = "select * from customer WHERE cid=" + cid;
    ResultSet rs = getData(sql);
    try {
      while (rs.next()) {
        String name = rs.getString(2);
        String phone = rs.getString(3);
        String address = rs.getString(4);
        String username = rs.getString(5);
        String password = rs.getString(6);
        int status = rs.getInt(7);
        cus = new Customer(cid, name, phone, address, username, password, status);
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
    }
    return cus;
  }

  public ArrayList<Customer> getAllCustomer() {
    ArrayList<Customer> arr = new ArrayList<>();
    String sql = "select * from customer";
    ResultSet rs = getData(sql);
    try {
      while (rs.next()) {
        int id = rs.getInt(1);
        String name = rs.getString(2);
        String phone = rs.getString(3);
        String address = rs.getString(4);
        String username = rs.getString(5);
        String password = rs.getString(6);
        int status = rs.getInt(7);
        Customer cus = new Customer(id, name, phone, address, username, password, status);
        arr.add(cus);
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
    }
    return arr;
  }

  public ResultSet getName(String name) {
    String sql = "select * from Customer where cname like '%" + name + "%'";
    return getData(sql);
  }

  public ResultSet getAddress(String address) {
    String sql = "select * from Customer where caddress like '%" + address + "%'";
    return getData(sql);
  }

  public ResultSet getUsername(String name) {
    String sql = "select * from Customer where username like '%" + name + "%'";
    return getData(sql);
  }

  public void showInfo(int cid) {
    String sql = "select * from customer where cid=" + cid;
    ResultSet rs = getData(sql);
    try {
      if (rs.next()) {
        String name = rs.getString(2);
        String phone = rs.getString(3);
        String address = rs.getString(4);
        String username = rs.getString(5);
        String password = rs.getString(6);
        int status = rs.getInt(7);
        Customer cus = new Customer(cid, name, phone, address, username, password, status);
        System.out.println(cus);
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public Customer checkLogin(String username, String password) {
    Customer cus = null;
    try {
      String sql = "SELECT * FROM customer WHERE username =? AND password=? AND status=1";
      PreparedStatement pre = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      pre.setString(1, username);
      pre.setString(2, password);

      ResultSet rs = pre.executeQuery();
      if (rs.first()) {
        int cid = rs.getInt(1);
        String cname = rs.getString(2);
        String cphone = rs.getString(3);
        String cadd = rs.getString(4);
        cus = new Customer(cid, cname, cphone, cadd, username, password, 1);
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
    }
    return cus;
  }

  public boolean checkExistUsername(String username) {
    String sql = "SELECT * FROM customer WHERE username='" + username + "'";
    ResultSet rs = getData(sql);
    try {
      if (rs.first()) {
        return true;
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }
}
