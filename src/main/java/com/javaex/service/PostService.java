package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.PostVo;

@Service
public class PostService {

	@Autowired
	private PostDao postDao;
	@Autowired
	private CategoryDao categoryDao;

	public Map <String, Object> getList(String id, int cateNo, int crtPage) {
		// cateNo 파라미터 없을 때 최근 카테고리 번호 얻기
		if(cateNo == 0) {
			cateNo = categoryDao.selectRecentCateNo(id);
		}
		
		// 블로그와 카테고리 찾기
		Map <String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("cateNo", cateNo);
		
		// 리스트 가져오기 
		crtPage = (crtPage> 0)? crtPage : 1;
		
		final int listCnt = 5;
		//-시작글 번호 
		int startRnum = (crtPage-1)*listCnt +1;
		//-끝글 번호
		int endRnum = (startRnum + listCnt) -1;
		
		// 페이징버튼
		
		//-전체 글갯수
		int totalCnt = postDao.selectTotal(cateNo);
		System.out.println(totalCnt);
		//-페이지당 버튼 갯수
		final int pageBtnCount = 3;
		
		//마지막 버튼 번호
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount) * pageBtnCount; 		
		
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo-pageBtnCount+1;
		
		//다음 화살표 유무
		boolean next = false;
		if(endPageBtnNo*listCnt <totalCnt) {
			next = true;
		}
		else { // 다음 화살표가 안보이면 마지막 버튼값 다시 계산
			endPageBtnNo = (int)Math.ceil(totalCnt/(double)listCnt);
		}
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		
		// 페이지 리스트 검색
		List<PostVo> postList = postDao.selectPageList(map);
		
		// 모델 데이터
		Map <String, Object> pMap = new HashMap<>();
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		pMap.put("postList", postList);
	
		return pMap;
	}

	public PostVo read(int postNo) {
		PostVo postVo = postDao.selectPost(postNo);
		postVo.setPostTitle(postVo.getPostTitle().replace(" ","&nbsp;"));
		postVo.setPostContent(postVo.getPostContent().replace(" ","&nbsp;").replace("\n", "<br>"));
		return postVo;
	}

	public void add(PostVo postVo) {
		postDao.insert(postVo);
		
	}
	
}