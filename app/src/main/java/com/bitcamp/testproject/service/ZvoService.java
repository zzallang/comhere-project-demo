package com.bitcamp.testproject.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

  public void uploadFile(HttpServletRequest req) throws IOException, ServletException {
    System.out.println("파일 업로드 서비스 호출 : " + req);
    File file = new File();
    String dirPath = sc.getRealPath("/test/files");
    Collection<Part> part = req.getParts();
    for(Part p : part) {
      System.out.println(p.getSubmittedFileName());
      p.getContentType();
      String filename = UUID.randomUUID().toString();
      p.write(dirPath + "/" + filename);
      file.setFile(filename);
      fileDao.uploadFile(file);
    }
  }

  public List<File> findAllFiles() {
    List<File> list = new ArrayList<>();
    fileDao.findAllFiles(list);
    System.out.println("파일 조회하기 file = " + fileDao.findAllFiles(list));
    return list;
  }

}
