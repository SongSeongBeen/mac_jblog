package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.SearchVo;

@Repository
public class SearchDao {

	@Autowired
	private SqlSession sqlSession;

	public List<SearchVo> selectByTitle(Map<String, Object> keyMap) {
		return sqlSession.selectList("search.selectByTitle", keyMap);
	}

	public List<SearchVo> selectByName(Map<String, Object> keyMap) {
		return sqlSession.selectList("search.selectByName", keyMap);
	}

	public int selectByTitleTotal(String keyword) {
		return sqlSession.selectOne("search.selectByTitleTotal", keyword);
	}

	public int selectByNameTotal(String keyword) {
		return sqlSession.selectOne("search.selectByNameTotal", keyword);
	}


}
