package com.bitcamp.testproject.service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bitcamp.testproject.dao.FileDao;
import com.bitcamp.testproject.dao.ZvoDao;
import com.bitcamp.testproject.vo.File;
import com.bitcamp.testproject.vo.Zvo;

@Service
public class ZvoService {

  @Autowired
  ZvoDao voDao;
  @Autowired
  FileDao fileDao;
  @Autowired
  ServletContext sc;

  public void insert(Zvo vo) {
    voDao.insert(vo);
  }

  public List<Zvo> findAll() {
    return voDao.findAll();
  }

  public void update(Zvo vo) throws Exception {
    int result = voDao.update(vo);
    if(result != 1) {
      throw new Exception("업데이트 실패@");
    }
  }

  public void delete(int no) {
    voDao.delete(no);
  }

  @Transactional
  public void uploadFile(HttpServletRequest req) throws IOException, ServletException {
    Collection<Part> part = req.getParts();
    for(Part p : part) {
      File file = commonFilesSetting(p);
      p.write(file.getFilePath() + "/" + file.getFileName() + "." + file.getFileExt());
      fileDao.uploadFile(file);
    }
  }

  @Transactional
  public void deleteFile(int no) {
    File file = findDeleteFile(no);
    System.out.println(file.getFilePath() + "\\" + file.getFileName() + "." + file.getFileExt());
    java.io.File targetFile = new java.io.File(file.getFilePath() + "\\" + file.getFileName() + "." + file.getFileExt()); 

    if (targetFile.exists()) {
      targetFile.delete();
    }

    fileDao.deleteFile(no);
  }

  public List<File> findAllFiles() {
    return fileDao.findAllFiles();
  }

  public String excludeFileExt(String filename) {
    return filename.substring(filename.indexOf(".") + 1);
  }

  @Transactional
  public File commonFilesSetting(Part p) {
    File file = new File();
    String filename = UUID.randomUUID().toString();
    String dirPath = sc.getRealPath("/test/files");

    file.setFileName(filename);
    file.setFileRealName(p.getSubmittedFileName());
    file.setFileExt(excludeFileExt(p.getSubmittedFileName()));
    file.setFileType(p.getContentType());
    file.setFileSize(p.getSize());
    file.setFilePath(dirPath);

    return file;
  }

  public File findDeleteFile(int fileNo) {
    return fileDao.findDeleteFile(fileNo);
  }

}
