package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<PostVo> selectList(Map<String, Object> map) {
		return sqlSession.selectList("post.selectList",map);
	}
	
	public List<PostVo> selectPageList(Map<String, Object> map) {
		return sqlSession.selectList("post.selectPageList",map);
	}
	
	
	public PostVo selectPost(int postNo) {
		return sqlSession.selectOne("post.selectPost",postNo);
	}

	public void insert(PostVo postVo) {
		sqlSession.insert("post.insert",postVo);
		
	}

	public int selectTotal(int cateNo) {
		return sqlSession.selectOne("post.selectTotal",cateNo);
	}

}
