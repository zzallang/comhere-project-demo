package com.bitcamp.testproject.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bitcamp.testproject.service.DataService;

@RestController //ResponseBody가 포함된 컨트롤러
@RequestMapping("/data/")
public class DataController {

  @Autowired
  DataService ds;

  @GetMapping("call")
  public int callData(String text) {
    ds.ajaxSave(text);
    return 1;
  }

}
