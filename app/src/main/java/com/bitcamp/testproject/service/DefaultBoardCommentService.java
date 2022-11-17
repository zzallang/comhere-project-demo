package com.bitcamp.testproject.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitcamp.testproject.dao.BoardCommentDao;
import com.bitcamp.testproject.vo.Board;
import com.bitcamp.testproject.vo.Comment;

@Service
public class DefaultBoardCommentService implements BoardCommentService {

  @Autowired 
  BoardCommentDao boardCommentDao;

  //  @Override
  //  public List<Comment> list(int boardNo, int pageNo) {
  //    return boardCommentDao.findByBoardNo(boardNo, pageNo);
  //  }

  @Override
  public int insert(Comment comment) {
    return boardCommentDao.insertComment(comment);
  }

  @Override
  public List<Comment> getComments(Map<String, Object> map) {
    return boardCommentDao.findByBoardNo(map);
  }

  @Override
  public Comment getComment(int commentNo) {
    return boardCommentDao.findCommentByNo(commentNo);
  }

  @Override
  public int delete(int boardNo) {
    return boardCommentDao.deleteComment(boardNo);
  }

  @Override
  public int update(Comment comment) {
    return boardCommentDao.updateComment(comment);
  }

  @Override
  public int countCommentListTotal(int boardNo) {
    return boardCommentDao.findCommentsCount(boardNo);
  }

  @Override
  public int deleteAll(int boardNo) {
    return boardCommentDao.deleteAll(boardNo);
  }

  // 은지
  // 마이페이지 작성 댓글
  @Override
  public List<Board> findByMyComment(Map<String, Object> paramMap) throws Exception {
    return boardCommentDao.findByMyComment(paramMap);
  }

  @Override
  public int countMyComment(int memberNo) throws Exception {
    return boardCommentDao.countMyComment(memberNo);
  }

}








