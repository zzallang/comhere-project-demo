package com.bitcamp.testproject.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bitcamp.testproject.dao.BoardDao;
import com.bitcamp.testproject.dao.FavoriteRegionDao;
import com.bitcamp.testproject.dao.FavoriteSportsDao;
import com.bitcamp.testproject.dao.MemberDao;
import com.bitcamp.testproject.vo.FavoriteRegion;
import com.bitcamp.testproject.vo.FavoriteSports;
import com.bitcamp.testproject.vo.Member;

@Service 
public class DefaultMemberService implements MemberService {

  @Autowired
  ServletContext sc;

  @Autowired
  MemberDao memberDao;

  @Autowired
  BoardDao boardDao;

  @Autowired
  FavoriteRegionDao favoriteRegionDao;

  @Autowired
  FavoriteSportsDao favoriteSprotsDao;

  @Transactional
  @Override
  public void add(Member member, Part file) throws Exception {

    String dirPath = sc.getRealPath("/member/files");

    if (file != null) {
      String filename = UUID.randomUUID().toString();
      file.write(dirPath + "/" + filename);
      member.setFilepath(filename);
    }

    member.setFavoriteRegion(saveRegion(member));
    member.setFavoriteSports(saveSports(member));

    System.out.println("member=" + member);

    // 1) 회원등록
    memberDao.insert(member);

    // 2) 관심지역 등록
    favoriteRegionDao.insertRegion(member);

    //  3) 관심운동 등록
    favoriteSprotsDao.insertSports(member);
  }

  @Transactional
  @Override
  public boolean update(Member member, Part file, int memberNo) throws Exception {
    member.setNo(memberNo);
    member.setFilepath(saveAttachedFile(file));
    member.setFavoriteRegion(saveRegion(member));
    member.setFavoriteSports(saveSports(member));
    if (member.getFilepath() == null) {
      String originFile = memberDao.getFileByMemberNo(member.getNo());
      member.setFilepath(originFile);
    }
    return memberDao.update(member) > 0;
  }

  @Override
  public boolean updatePW(String password, String email, String id) throws Exception {
    if (memberDao.updatePW(password, email, id) == 0) {
      return false;
    }
    return true;
  }

  @Override
  public Member get(int no) throws Exception {
    return memberDao.findByNo(no);
  }

  @Override
  public Member get(String id, String password) throws Exception {
    return memberDao.findByIdPassword(id, password);
  }


  @Override
  public Member getByPassword(String id, String email, String SecCode) throws Exception {
    return memberDao.findByPassword(id, email, SecCode);
  }

  @Override
  public Member getId(String name, String email) throws Exception {
    return memberDao.findById(name, email);
  }


  @Transactional
  @Override
  public boolean delete(int no) throws Exception {
    //    boardDao.deleteFilesByMemberBoards(no); // 회원이 작성한 게시글의 모든 첨부파일 삭제
    //    boardDao.deleteByMember(no); // 회원이 작성한 게시글 삭제
    return memberDao.delete(no) > 0; // 회원 삭제
  }

  @Override
  public List<Member> list() throws Exception {
    return memberDao.findAll();
  }

  @Override
  public int idCheck(String id) throws Exception {
    return memberDao.idCheck(id);
  }

  @Override
  public int verificationPw(String password, int no) throws Exception {
    return memberDao.verificationPw(password, no);
  }

  @Override
  public int nickCheck(String nickname) throws Exception {
    return memberDao.nickCheck(nickname);
  }

  @Override
  public int emailCheck(String email) throws Exception {
    return memberDao.emailCheck(email);
  }

  @Override
  public Member idEmailCheck(String id, String email) throws Exception {
    return memberDao.idEmailCheck(id, email);
  }

  @Override
  public Member idPasswordCheck(String id, String password) throws Exception {
    return memberDao.idPasswordCheck(id, password);
  }

  @Override
  public Member findIdCheck(String name, String email) throws Exception {
    return memberDao.findIdCheck(name, email);
  }

  public List<FavoriteRegion> saveRegion(Member member) {
    List<FavoriteRegion> favoriteRegion = new ArrayList<>();
    for (int no : member.getRegionDomain()) {
      favoriteRegion.add(new FavoriteRegion(no));
    }

    return favoriteRegion;
  }

  public List<FavoriteSports> saveSports(Member member) {
    List<FavoriteSports> favoriteSports = new ArrayList<>();
    for (int no : member.getSportsDomain()) {
      favoriteSports.add(new FavoriteSports(no));
    }

    return favoriteSports;
  }

  private String saveAttachedFile(Part file) throws IOException, ServletException {
    String dirPath = sc.getRealPath("/member/files");
    // 첨부파일이 있다면 실행
    if (file.getSize() != 0) {
      String filename = UUID.randomUUID().toString();
      file.write(dirPath + "/" + filename);
      System.out.println(filename + "\n파일네임 들어왔냐!>!>!");
      return filename;
    }
    return null;
  }
}






