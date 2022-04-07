package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	public void insertBlog(BlogVo blogVo) {
		sqlSession.insert("blog.insertBlog", blogVo);
	}

	public BlogVo selectBlog(String id) {
		return sqlSession.selectOne("blog.selectBlog",id);
	}

	public void updateImage(BlogVo blogVo) {
		sqlSession.update("blog.updateImage", blogVo);
		
	}

	public void updateTitle(BlogVo blogVo) {
		sqlSession.update("blog.updateTitle", blogVo);
	}

}
