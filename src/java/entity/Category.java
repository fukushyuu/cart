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
public class Category {

  private int cateID; // primary key identity(1,1)
  private String cateName;
  private int status;

  public Category() {
  }

  public Category(int cateID, String cateName, int status) {
    this.cateID = cateID;
    this.cateName = cateName;
    this.status = status;
  }

  public Category(String cateName) {
    this.cateName = cateName;
  }

  public int getCateID() {
    return cateID;
  }

  public void setCateID(int cateID) {
    this.cateID = cateID;
  }

  public String getCateName() {
    return cateName;
  }

  public void setCateName(String cateName) {
    this.cateName = cateName;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Category{" + "cateID=" + cateID + ", cateName=" + cateName + ", status=" + status + '}';
  }
  
  
}
