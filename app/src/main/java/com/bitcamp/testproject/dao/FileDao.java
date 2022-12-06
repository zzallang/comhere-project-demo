package com.bitcamp.testproject.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bitcamp.testproject.vo.File;

@Mapper
public interface FileDao {

  void uploadFile(File file);

  List<File> findAllFiles(List<File> list);
}
