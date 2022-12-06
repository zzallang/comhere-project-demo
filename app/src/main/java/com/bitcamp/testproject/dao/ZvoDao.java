package com.bitcamp.testproject.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bitcamp.testproject.vo.Zvo;

@Mapper
public interface ZvoDao {

  void insert(Zvo vo);

  List<Zvo> findAll();

  int update(Zvo vo);

  void delete(int no);

}
