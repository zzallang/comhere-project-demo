package com.bitcamp.testproject.vo;

public class File {

  private int no;
  private String file;

  @Override
  public String toString() {
    return "File [no=" + no + ", file=" + file + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getFile() {
    return file;
  }

  public void setFile(String file) {
    this.file = file;
  }


}
