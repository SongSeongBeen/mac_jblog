package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int insertUser(UserVo userVo) {
		sqlSession.insert("user.insertUser",userVo);
		return userVo.getUserNo();
	}
	// 세션 UserVo
	public UserVo selectAuthUser(UserVo userVo) {	
		return sqlSession.selectOne("user.selectAuthUser",userVo);
	}
	// 블로그 생성 UserVo
	public UserVo selectUser(int userNo) {			
		return sqlSession.selectOne("user.selectUser", userNo);
	}
	// 중복체크 count
	public int selectUidCnt(String id) {			
		return sqlSession.selectOne("user.selectUidCnt", id);
	}
	// 블로그 UserVo
	public UserVo selectBlogUser(String id) {	
		return sqlSession.selectOne("user.selectBlogUser", id);
	}

	
	
}
