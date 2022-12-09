package com.bitcamp.testproject.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //ResponseBody가 포함된 컨트롤러
@RequestMapping("/data/")
public class DataController {

  @GetMapping("call")
  public int callData(int no) {
    no = 2;
    return no;
  }

}
