package com.bitcamp.testproject.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.bitcamp.testproject.service.MemberService;
import com.bitcamp.testproject.vo.FavoriteRegion;
import com.bitcamp.testproject.vo.FavoriteSports;
import com.bitcamp.testproject.vo.Member;

@Controller 
@RequestMapping("/auth/")
public class AuthController {

  MemberService memberService;

  public AuthController(MemberService memberService) {
    System.out.println("AuthController() 호출됨!");
    this.memberService = memberService;
  }

  @GetMapping("form") 
  public String form(@CookieValue(name="id",defaultValue="") String id, Model model) throws Exception {
    model.addAttribute("id", id);
    return "auth/form";
  }

  @PostMapping("login") 
  public ModelAndView login(
      String id,
      String password, 
      HttpServletResponse response,
      HttpSession session) throws Exception {

    Member member = memberService.get(id, password);

    if (member != null) {
      session.setAttribute("loginMember", member); 
    }

    Cookie cookie = new Cookie("id", id); 
    if (id == null) {
      cookie.setMaxAge(0); 
    } else {
      cookie.setMaxAge(60 * 60 * 24 * 7); // 7일
    }
    response.addCookie(cookie); 

    ModelAndView mv = new ModelAndView("auth/loginResult");
    mv.addObject("member", member);
    return mv;
  }

  @GetMapping("findId")
  public String findId() {
    return "auth/findId";
  }

  @GetMapping("findPassword")
  public String findIdPassword() {
    return "auth/findPassword";
  }


  @GetMapping("logout") 
  public String logout(HttpSession session) throws Exception {
    session.invalidate(); 
    return "redirect:../"; 
  }


  // 은지
  @GetMapping("join")
  public String form(Model model) throws Exception {
    model.addAttribute("data", "join page");

    return "auth/join";
  }

  @PostMapping("addjoin")
  public ModelAndView add(Member member, int[] region_domain, int[] sports_domain) throws Exception {
    member.setFavoriteRegion(saveRegion(region_domain));
    member.setFavoriteSports(saveSports(sports_domain));

    memberService.add(member);
    ModelAndView mv = new ModelAndView("redirect:form");
    return mv;
  }

  @GetMapping("mypage-member")
  public String myPageMember(Member member) {

    return "myPageMember";
  }

  // 아이디 중복 체크 확인
  @PostMapping("id-check")
  @ResponseBody
  public String idCheck(Member member) throws Exception {
    return memberService.checkId(member);
  }


  public List<FavoriteRegion> saveRegion(int[] region_domain) {
    List<FavoriteRegion> favoriteRegion = new ArrayList<>();
    for (int no : region_domain) {
      favoriteRegion.add(new FavoriteRegion(no));
    }

    return favoriteRegion;
  }

  public List<FavoriteSports> saveSports(int[] region_domain) {
    List<FavoriteSports> favoriteSports = new ArrayList<>();
    for (int no : region_domain) {
      favoriteSports.add(new FavoriteSports(no));
    }

    return favoriteSports;
  }

}






