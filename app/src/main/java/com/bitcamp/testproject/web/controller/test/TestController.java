package com.bitcamp.testproject.web.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bitcamp.testproject.service.ZvoService;
import com.bitcamp.testproject.vo.Zvo;

@Controller
@RequestMapping("/test/")
public class TestController {

  @Autowired
  ZvoService vs;

  @GetMapping("profile")
  public String profileViewer(Model model) {
    model.addAttribute("list", vs.findAll());
    return "test/profile";
  }

  @PostMapping("insert")
  public String insert(Zvo vo) {
    vs.insert(vo);
    return "redirect:profile";
  }

  @PostMapping("edit")
  public String update(Zvo vo) throws Exception {
    vs.update(vo);
    return "redirect:profile";
  }

  @PostMapping("remove")
  public String delete(int no) {
    vs.delete(no);
    return "redirect:profile";
  }


}
