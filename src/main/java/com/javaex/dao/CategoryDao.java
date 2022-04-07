package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public void insertCategory(CategoryVo categoryVo) {
		sqlSession.insert("category.insertCategory", categoryVo);
	}
	
	public List<CategoryVo> selectCateList(String id) {
		return sqlSession.selectList("category.selectCateList", id);
	}


	public List<CategoryVo> selectAdminCateList(String id) {
		return sqlSession.selectList("category.selectAdminCateList", id);
	}


	public int delete(int cateNo) {
		return sqlSession.delete("category.delete",cateNo);
	}


	public void insertSelectCate(CategoryVo categoryVo) {
		sqlSession.insert("category.insertSelectCate", categoryVo);
	}

	public CategoryVo selectCategory(int cateNo) {
		return sqlSession.selectOne("category.selectCategory", cateNo);
	}

	public List<CategoryVo> selectOptCateList(String id) {
		return sqlSession.selectList("category.selectOptCateList", id);
	}

	public int selectRecentCateNo(String id) {
		return sqlSession.selectOne("category.selectRecentCateNo",id);
	}
	
	
}
