/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Nhat Nam
 */
public class BillDetail {

  private String pid, oID;
  private int quantity;
  private double price, total;

  public BillDetail() {
  }

  public BillDetail(String pid, String oID, int quantity, double price, double total) {
    this.pid = pid;
    this.oID = oID;
    this.quantity = quantity;
    this.price = price;
    this.total = total;
  }

  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }

  public String getoID() {
    return oID;
  }

  public void setoID(String oID) {
    this.oID = oID;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  @Override
  public String toString() {
    return "BillDetail{" + "pid=" + pid + ", oID=" + oID + ", quantity=" + quantity + ", price=" + price + ", total=" + total + '}';
  }

}
