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
public class Test {

  @Autowired
  ZvoService vs;

  @GetMapping("profile")
  public String profileViewer(Model model) {
    System.out.println(vs.findAll());
    model.addAttribute("list", vs.findAll());
    return "test/profile";
  }

  @PostMapping("insert")
  public String insert(Zvo vo) {
    System.out.println("컨트롤러 추가 >>>>>>>>>>>>>> " + vo.toString());
    vs.insert(vo);
    return "redirect:profile";
  }

  @PostMapping("edit")
  public String update(Zvo vo) throws Exception {
    System.out.println("컨트롤러 업뎃 >>>>>>>>>>>>>> " + vo.toString());
    vs.update(vo);
    return "redirect:profile";
  }

  @GetMapping("remove")
  public String delete() {
    System.out.println("컨트롤러 삭제 >>>>>>>>>>");
    return "redirect:profile";
  }


}
