/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Bill;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nhat Nam
 */
public class DAOBill extends DBConnect {

  public int addBill(Bill bill) {
    int n = 0;
    String sql = "insert into bill (oID,cname,cphone,cAddress,total,cid) values(?,?,?,?,?,?)";
    try {
      PreparedStatement pre = conn.prepareStatement(sql);
      pre.setString(1, bill.getoID());
      pre.setString(2, bill.getCname());
      pre.setString(3, bill.getCphone());
      pre.setString(4, bill.getcAddress());
      pre.setDouble(5, bill.getTotal());
      pre.setInt(6, bill.getCid());
      n = pre.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
    }

    return n;

  }

  public int updateBill(Bill bill) {
    int n = 0;
    String sql = "update bill set cname=?,cphone=?,cAddress=?,total=?,cid=? where oID=?";
    try {
      PreparedStatement pre = conn.prepareStatement(sql);
      pre.setString(1, bill.getCname());
      pre.setString(2, bill.getCphone());
      pre.setString(3, bill.getcAddress());
      pre.setDouble(4, bill.getTotal());
      pre.setInt(5, bill.getCid());
      pre.setString(6, bill.getoID());
      n = pre.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
    }
    return n;
  }

  public int changeInfo(Bill bill) {
    int n = 0;
    String sql = "update bill set cname=?,cphone=?,cAddress=?,status=? where oID=?";
    try {
      PreparedStatement pre = conn.prepareStatement(sql);
      pre.setString(1, bill.getCname());
      pre.setString(2, bill.getCphone());
      pre.setString(3, bill.getcAddress());
      pre.setInt(4, bill.getStatus());
      pre.setString(5, bill.getoID());
      n = pre.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
    }
    return n;
  }

  public int changeStatus(String oID, int status) {
    int n = 0;
    String sql = "update bill set status=? where oid=?";
    try {
      PreparedStatement pre = conn.prepareStatement(sql);
      pre.setInt(1, status);
      pre.setString(2, oID);
      n = pre.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
    }
    return n;
  }

  public void displayAll() {
    String sql = "select * from bill";
    ResultSet rs = getData(sql);
    try {
      while (rs.next()) {
        String id = rs.getString(1);
        String date = rs.getString(2);
        String name = rs.getString(3);
        String phone = rs.getString(4);
        String address = rs.getString(5);
        double total = rs.getDouble(6);
        int status = rs.getInt(7);
        int cid = rs.getInt(8);
        Bill bill = new Bill(id, date, name, phone, address, total, status, cid);
        System.out.println(bill);
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public ArrayList<Bill> getBill() {
    ArrayList<Bill> arr = new ArrayList<>();
    String sql = "select * from bill";
    ResultSet rs = getData(sql);
    try {
      while (rs.next()) {
        String id = rs.getString(1);
        String date = rs.getString(2);
        String name = rs.getString(3);
        String phone = rs.getString(4);
        String address = rs.getString(5);
        double total = rs.getDouble(6);
        int status = rs.getInt(7);
        int cid = rs.getInt(8);
        Bill bill = new Bill(id, date, name, phone, address, total, status, cid);
        arr.add(bill);
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
    }
    return arr;
  }

  public ResultSet getDate(String from, String to) {
    String sql = "select * from bill where dateCreate between '" + from + "' and '" + to + "'";
    return getData(sql);
  }

  public ResultSet getName(String name) {
    String sql = "select * from bill where cname like '%" + name + "%'";
    return getData(sql);
  }

  public ResultSet getAddress(String address) {
    String sql = "select * from bill where caddress like '%" + address + "%'";
    return getData(sql);
  }

  public ResultSet getTotal(double from, double to) {
    String sql = "select * from bill where total between '" + from + "' and '" + to + "'";
    return getData(sql);
  }

  public ResultSet getCID(int cid) {
    String sql = "select * from bill where cid=" + cid;
    return getData(sql);
  }

  public void showInfo(String oid) {
    String sql = "select * from bill where oid='" + oid + "'";
    ResultSet rs = getData(sql);
    try {
      if (rs.next()) {
        String date = rs.getString(2);
        String name = rs.getString(3);
        String phone = rs.getString(4);
        String address = rs.getString(5);
        double total = rs.getDouble(6);
        int status = rs.getInt(7);
        int cid = rs.getInt(8);
        Bill bill = new Bill(oid, date, name, phone, address, total, status, cid);
        System.out.println(bill);
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static void main(String[] args) {
    DAOBill dao = new DAOBill();

//    dao.addBill(new Bill("or002", "Tran Nguyen H", null, "HN", 1200, 4));
//    dao.addBill(new Bill("or003", "Dao Thi L", null, "HN", 1200, 5));
//    dao.addBill(new Bill("or004", "Le Van N", null, "HN", 1200, 6));
//    dao.addBill(new Bill("or005", "Hoang Huy K", null, "HN", 1200, 2));
//    dao.getInfo("or001");
//    ResultSet rs = dao.getDate("2021-9-15", "2021-9-16");
    ResultSet rs = dao.getTotal(600, 1200);
    try {
      while (rs.next()) {
        String id = rs.getString(1);
        String date = rs.getString(2);
        String name = rs.getString(3);
        String phone = rs.getString(4);
        String address = rs.getString(5);
        double total = rs.getDouble(6);
        int status = rs.getInt(7);
        int cid = rs.getInt(8);
        Bill bill = new Bill(id, date, name, phone, address, total, status, cid);
        System.out.println(bill);
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
