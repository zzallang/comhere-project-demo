package com.bitcamp.testproject.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.bitcamp.testproject.vo.Board;
import com.bitcamp.testproject.vo.Comment;

@Mapper
public interface BoardCommentDao {

  //  List<Map<String, Object>> findAll(int boardNo);

  int insertComment(Comment comment);

  List<Comment> findByBoardNo(Map<String, Object> map);

  Comment findCommentByNo(int commentNo);

  int deleteComment(int boardNo);

  int updateComment(Comment comment);

  int findCommentsCount(int boardNo);

  int deleteAll(int boardNo);

  // 은지
  // 마이페이지 작성 댓글
  List<Board> findByMyComment(Map<String, Object> paramMap);

  int countMyComment(int memberNo);

}
