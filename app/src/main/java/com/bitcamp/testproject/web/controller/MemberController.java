package com.bitcamp.testproject.web.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.bitcamp.testproject.service.FavoriteRegionService;
import com.bitcamp.testproject.service.FavoriteSportsService;
import com.bitcamp.testproject.service.MemberService;
import com.bitcamp.testproject.service.RegionService;
import com.bitcamp.testproject.service.SportsService;
import com.bitcamp.testproject.vo.Member;

@Controller
@RequestMapping("/member/")
public class MemberController {

  @Autowired
  ServletContext sc;
  @Autowired
  MemberService memberService;
  @Autowired
  RegionService regionService;
  @Autowired
  SportsService sportsService;
  @Autowired
  FavoriteRegionService favoriteRegionService;
  @Autowired
  FavoriteSportsService favoriteSportsService;

  // 은지
  @GetMapping("join")
  public String form(Model model) throws Exception {
    model.addAttribute("regionList", regionService.list());
    model.addAttribute("sportsList", sportsService.list());
    return "auth/join";
  }

  @PostMapping("addjoin")
  public ModelAndView add(Member member, Part file) throws Exception {
    memberService.add(member, file);
    ModelAndView mv = new ModelAndView("redirect:../auth/form");
    return mv;
  }

  @GetMapping("viewer")
  public String passwordCheckViewer() {
    return "member/pwCheckViewer";
  }

  @GetMapping("my-info")
  public String confirmation(HttpSession session, Model model) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    model.addAttribute("member", memberService.get(loginMember.getNo()));
    model.addAttribute("regionList", regionService.list());
    model.addAttribute("sportsList", sportsService.list());
    return "member/myInfo";
  }

  @Transactional
  @PostMapping("update")
  public ModelAndView myPageMember(HttpSession session, Member member, Part file) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    favoriteRegionService.deleteFavoriteRegion(loginMember.getNo());
    favoriteSportsService.deleteFavoriteSports(loginMember.getNo());

    memberService.update(member,file, loginMember.getNo());

    favoriteRegionService.addFavoriteRegion(member);
    favoriteSportsService.addFavoriteSports(member);

    ModelAndView mv = new ModelAndView("redirect:my-info");
    return mv;
  }

  @GetMapping("delete")
  @ResponseBody
  public boolean deleteMember(int no, HttpSession session) throws Exception{
    boolean result = memberService.delete(no);
    if (result) {
      session.invalidate();
    } else {
      result = false;
    }
    return result;
  }

  @GetMapping("delete_pw_check_viewer")
  public String deletePwCehckViewer(int no, Model model) {
    model.addAttribute("no", no);
    return "member/delete_pw_check_viewer";
  }

  @PostMapping("duplication-id-check")
  @ResponseBody
  public int idCheck(String id) throws Exception{
    int result = memberService.idCheck(id); 
    return result;
  }

  @PostMapping("duplication-email-check")
  @ResponseBody
  public int emailCheck(String email) throws Exception{
    int result = memberService.emailCheck(email); 
    return result;
  }

  @PostMapping("duplication-nick-check")
  @ResponseBody
  public int nickCheck(String nickname) throws Exception{
    int result = memberService.nickCheck(nickname);
    return result;
  }

  @PostMapping("verification-pw-check")
  @ResponseBody
  public int pWCheck(HttpSession session, String password) throws Exception{
    Member loginMember = (Member) session.getAttribute("loginMember");
    System.out.println(password);
    System.out.println(loginMember);
    int result = memberService.verificationPw(password, loginMember.getNo());
    return result;
  }


}
