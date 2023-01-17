package com.bitcamp.testproject.service;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitcamp.testproject.dao.ZvoDao;

@Service
public class DataService {

  @Autowired
  ZvoDao vd;

  public String ajaxSave(String text) {
    // id = pk
    int no = 6;
    vd.findCountById(no);
    System.out.println(vd.findCountById(no));
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("no", no);
    map.put("text", text);
    if(vd.findCountById(no) == 1) {
      //      vd.updateText(no,text);
      vd.updateText(map);
    } else {
      vd.insertAjax(text);
    }
    return null;
  }

}
