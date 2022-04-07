package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CommentsVo;

@Repository
public class CommentsDao {

	@Autowired
	private SqlSession sqlSession;

	public void insertSelectNo(CommentsVo commentsVo) {
		sqlSession.insert("comments.insertSelectNo",commentsVo);
	}

	public CommentsVo selectComment(int cmtNo) {
		return sqlSession.selectOne("comments.selectComment", cmtNo);
	}

	public List<CommentsVo> selectCmtList(int postNo) {
		return sqlSession.selectList("comments.selectCmtList", postNo);
	}

	public int deleteCmt(int cmtNo) {
		return sqlSession.delete("comments.deleteCmt", cmtNo);
	}
	
	
}
