package com.bitcamp.testproject.vo;

import java.sql.Date;

public class Zvo {

  private int no;
  private String name;
  private Date bday;
  private String gender;
  private String tel;
  private String addr;

  @Override
  public String toString() {
    return "Zvo [no=" + no + ", name=" + name + ", bday=" + bday + ", gender=" + gender + ", tel="
        + tel + ", addr=" + addr + "]";
  }

  public int getNo() {
    return no;
  }


  public void setNo(int no) {
    this.no = no;
  }


  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public Date getBday() {
    return bday;
  }


  public void setBday(Date bday) {
    this.bday = bday;
  }


  public String getGender() {
    return gender;
  }


  public void setGender(String gender) {
    this.gender = gender;
  }


  public String getTel() {
    return tel;
  }


  public void setTel(String tel) {
    this.tel = tel;
  }


  public String getAddr() {
    return addr;
  }


  public void setAddr(String addr) {
    this.addr = addr;
  }


}
