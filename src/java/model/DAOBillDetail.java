/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.BillDetail;
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
public class DAOBillDetail extends DBConnect {

  public int addBillDetail(BillDetail bdetail) {
    int n = 0;
    String sql = "insert into billdetail (pid ,oID , quantity , price , total) values(?,?,?,?,?)";
    try {
      PreparedStatement pre = conn.prepareStatement(sql);
      pre.setString(1, bdetail.getPid());
      pre.setString(2, bdetail.getoID());
      pre.setInt(3, bdetail.getQuantity());
      pre.setDouble(4, bdetail.getPrice());
      pre.setDouble(5, bdetail.getTotal());
      n = pre.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
    }
    return n;
  }

  public int updateBillDetail(BillDetail bdetail) {
    int n = 0;
    String sql = "update billdetail set quantity=? , price =?, total =? where pid=? and oID=?";
    try {
      PreparedStatement pre = conn.prepareStatement(sql);
      pre.setInt(1, bdetail.getQuantity());
      pre.setDouble(2, bdetail.getPrice());
      pre.setDouble(3, bdetail.getTotal());
      pre.setString(4, bdetail.getPid());
      pre.setString(5, bdetail.getoID());
      n = pre.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
    }

    return n;
  }

  public void displayAll() {
    String sql = "select * from billdetail";
    ResultSet rs = getData(sql);
    try {
      while (rs.next()) {
        String pid = rs.getString(1);
        String oid = rs.getString(2);
        int quantity = rs.getInt(3);
        double price = rs.getDouble(4);
        double total = rs.getDouble(5);
        BillDetail bill = new BillDetail(pid, oid, quantity, price, total);
        System.out.println(bill);
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void changeQuantity(String pid, String oid, int quan) {
    int n = 0;
    String sql = "update billdetail set quantity=? where pid=? and oid=?";

    try {
      PreparedStatement pre = conn.prepareStatement(sql);
      pre.setInt(1, quan);
      pre.setString(2, pid);
      pre.setString(3, oid);
      n = pre.executeUpdate();

    } catch (SQLException ex) {
      Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public ArrayList<BillDetail> getBillDetal() {
    ArrayList<BillDetail> arr = new ArrayList<>();
    String sql = "select * from billdetail";
    ResultSet rs = getData(sql);
    try {
      while (rs.next()) {
        String pid = rs.getString(1);
        String oid = rs.getString(2);
        int quantity = rs.getInt(3);
        double price = rs.getDouble(4);
        double total = rs.getDouble(5);
        BillDetail bill = new BillDetail(pid, oid, quantity, price, total);
        arr.add(bill);
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
    }
    return arr;
  }

  public ResultSet getPID(String pid) {
    String sql = "select * from billdetail where pid='" + pid + "'";
    return getData(sql);
  }

  public ResultSet getOID(String oid) {
    String sql = "select * from billdetail where pid='" + oid + "'";
    return getData(sql);
  }

  public void showInfo(String pid, String oid) {
    String sql = "select * from billdetail where pid='" + pid + "' and oid='" + oid + "'";
    ResultSet rs = getData(sql);
    try {
      if (rs.next()) {

        int quantity = rs.getInt(3);
        double price = rs.getDouble(4);
        double total = rs.getDouble(5);
        BillDetail bill = new BillDetail(pid, oid, quantity, price, total);
        System.out.println(bill);
      }
    } catch (SQLException ex) {
      Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static void main(String[] args) {
    DAOBillDetail dao = new DAOBillDetail();

//    dao.addBillDetail(new BillDetail("a01", "or001", 1, 1200, 1200));
//    dao.displayAll();
    dao.updateTotal("a01", "or001", 2, 1200);
//    try {
//      while (rs.next()) {
//        String pid = rs.getString(1);
//        String oid = rs.getString(2);
//        int quantity = rs.getInt(3);
//        double price = rs.getDouble(4);
//        double total = rs.getDouble(5);
//        BillDetail bill = new BillDetail(pid, oid, quantity, price, total);
//        System.out.println(bill);
//      }
//    } catch (SQLException ex) {
//      Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
//    }
  }

  public void updateTotal(String pid, String oid, int quan, double pri) {
    int n = 0;
    String sql = "update billdetail set total=? where pid=? and oid=?";

    try {
      PreparedStatement pre = conn.prepareStatement(sql);
      pre.setDouble(1, quan * pri);
      pre.setString(2, pid);
      pre.setString(3, oid);
      n = pre.executeUpdate();

    } catch (SQLException ex) {
      Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
