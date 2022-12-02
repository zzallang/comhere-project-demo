package com.bitcamp.testproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitcamp.testproject.dao.ZvoDao;
import com.bitcamp.testproject.vo.Zvo;

@Service
public class ZvoService {

  @Autowired
  ZvoDao voDao;

  public void insert(Zvo vo) {
    System.out.println("서비스 >>>>>>>>>>>>>>>> " + vo.toString());
    voDao.insert(vo);
  }

  public List<Zvo> findAll() {
    System.out.println("서비스 전부 델고 왔냐");
    return voDao.findAll();
  }

  public void update(Zvo vo) throws Exception {
    int result = voDao.update(vo);
    if(result != 1) {
      throw new Exception("업데이트 실패@");
    }
  }



}
