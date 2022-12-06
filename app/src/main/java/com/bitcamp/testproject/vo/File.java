package com.bitcamp.testproject.vo;

public class File {

  private int no;
  private String fileName;
  private String fileRealName;
  private String fileExt;
  private long fileSize;
  private String fileType;
  private String filePath;

  @Override
  public String toString() {
    return "File [no=" + no + ", fileName=" + fileName + ", fileRealName=" + fileRealName
        + ", fileExt=" + fileExt + ", fileSize=" + fileSize + ", fileType=" + fileType
        + ", filePath=" + filePath + "]";
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getFileRealName() {
    return fileRealName;
  }

  public void setFileRealName(String fileRealName) {
    this.fileRealName = fileRealName;
  }

  public String getFileExt() {
    return fileExt;
  }

  public void setFileExt(String fileExt) {
    this.fileExt = fileExt;
  }

  public long getFileSize() {
    return fileSize;
  }

  public void setFileSize(long fileSize) {
    this.fileSize = fileSize;
  }

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

}
