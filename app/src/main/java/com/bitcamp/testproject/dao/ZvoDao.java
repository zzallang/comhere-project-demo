package com.bitcamp.testproject.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bitcamp.testproject.vo.Zvo;

@Mapper
public interface ZvoDao {

  void insert(Zvo vo);

  List<Zvo> findAll();

  int update(Zvo vo);

  void delete(int no);

  public void insertAjax(String text);

  public int findCountById(int no);

  public int updateText(HashMap<String, Object> map);

}
