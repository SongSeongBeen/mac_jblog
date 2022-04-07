package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	public List<CategoryVo> getCateList(String id) {
		
		/* 카테고리 리스트 */
		return categoryDao.selectCateList(id);
	}
	
	public List<CategoryVo> getAdminCateList(String id){
		List<CategoryVo> cateList = categoryDao.selectAdminCateList(id);
		
		return cateList;
	}

	public boolean remove(int cateNo) {
		int count = categoryDao.delete(cateNo);
		
		if(count>0)
			return true;
		else
			return false;
	}

	public CategoryVo add(CategoryVo categoryVo) {
		categoryDao.insertSelectCate(categoryVo);
		int cateNo = categoryVo.getCateNo();
		
		return categoryDao.selectCategory(cateNo);
	}

	public List<CategoryVo> getOptCateList(String id) {
		return categoryDao.selectOptCateList(id);
	}
	
}
