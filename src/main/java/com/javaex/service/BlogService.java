package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Service
public class BlogService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	
	public UserVo getBlogUser(String id) {
		/* 블로그 user 가져오기 */
		return userDao.selectBlogUser(id);
	}
	
	public BlogVo getBlog(String id) {
		return blogDao.selectBlog(id);
	}
	
	public boolean adminChk(String id, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null || !id.equals(authUser.getId()))
			return false;
		else
			return true;
	}

	public void modify(String id, MultipartFile file, BlogVo blogVo) {
		
		blogVo.setId(id);
		
		//파일 사이즈
		long fileSize = file.getSize();
		
		 // 파일이 선택되면 
		if(fileSize > 0) {
			
			String saveDir = "C:\\javaStudy\\upload";
			
			// 기존 파일 삭제
			final String defaultLogo = "spring-logo.jpg";
			BlogVo LogoVo = blogDao.selectBlog(id);
			String existingLogo = LogoVo.getLogoFile();
			
			if(!existingLogo.equals(defaultLogo)) { // 기본 이미지가 아닐 때 삭제
				String path = saveDir+"\\"+existingLogo;
				File delFile = new File(path);
				delFile.delete();
			}
			
			//원본파일 이름
			String orgName = file.getOriginalFilename();
			//확장자
			String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			//저장파일 이름
			String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
			//파일 경로
			String filePath = saveDir+"\\"+saveName;
			
			//파일을 하드디스크에 저장(운영)
			try {
				byte[] fileData = file.getBytes();
				BufferedOutputStream bout = new BufferedOutputStream( new FileOutputStream(filePath));
				bout.write(fileData);
				bout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			blogVo.setLogoFile(saveName); 
			blogDao.updateImage(blogVo); // id + blogTitle + logoFile
		}
		
		// 파일이 선택되지 않았을 때
		else {
			blogDao.updateTitle(blogVo); // id + blogTitle	
		}
		
		
	}

}
