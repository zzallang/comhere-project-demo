package com.bitcamp.testproject.web.controller.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bitcamp.testproject.service.ZvoService;

@Controller
@RequestMapping("/test/")
public class FileController {

  @Autowired
  ZvoService vs;

  @GetMapping("file")
  public String uploadViewer(Model model) {
    model.addAttribute("fileList", vs.findAllFiles());
    System.out.println(vs.findAllFiles());
    return "test/file";
  }

  @PostMapping("upload-file")
  public String uploadFile(HttpServletRequest req) throws IOException, ServletException {
    vs.uploadFile(req);
    return "redirect:file";
  }


}
