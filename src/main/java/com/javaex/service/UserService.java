package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	
	public void join(UserVo userVo) {
		//회원가입
		int userNo = userDao.insertUser(userVo);		// 가입한 회원의 회원번호
		
		UserVo joinUser = userDao.selectUser(userNo);	// 회원번호로 id와 name 조회
		String id = joinUser.getId();
		String userName = joinUser.getUserName();
		
		//개인 블로그 생성
		BlogVo blogVo = new BlogVo();
		blogVo.setId(id);
		blogVo.setBlogTitle(userName+"의 블로그입니다.");
		blogVo.setLogoFile("spring-logo.jpg");
		blogDao.insertBlog( blogVo );
		
		//카테고리 초기화
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setId(id);
		categoryVo.setCateName("미분류");
		categoryVo.setDescription("기본으로 만들어지는 카테고리입니다.");
		categoryDao.insertCategory(categoryVo);
		
	}

	public UserVo login(UserVo userVo) {
		return userDao.selectAuthUser(userVo);
	}

	public boolean dupCheck(String id) {
		int count = userDao.selectUidCnt(id);
		if(count > 0)
			return true; // 중복임
		else
			return false; // 중복아님
	}

	public void initUser() {
		
		for(int i=0; i<50;i++) {
			UserVo userVo = new UserVo();
			userVo.setId("autoid"+i);
			userVo.setPassword("1234");
			userVo.setUserName("이름");
			join(userVo);
		}
		
	}

	
	
}
