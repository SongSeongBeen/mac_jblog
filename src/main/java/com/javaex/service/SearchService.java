package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.SearchDao;
import com.javaex.vo.SearchVo;

@Service
public class SearchService {

	@Autowired
	private SearchDao searchDao;

	public Map<String, Object> Search(String keyword, String kwdOpt, int crtPage) {
		
		// 블로그와 카테고리 찾기
		Map <String, Object> keyMap = new HashMap<>();
		
		// 리스트 가져오기 
		crtPage = (crtPage> 0)? crtPage : 1;
		
		final int listCnt = 5;
		//-시작글 번호 
		int startRnum = (crtPage-1)*listCnt +1;
		//-끝글 번호
		int endRnum = (startRnum + listCnt) -1;
		
		// 페이징버튼
		
		//-전체 글갯수
		int totalCnt;
		if("optTitle".equals(kwdOpt)) 
			totalCnt = searchDao.selectByTitleTotal(keyword);
		else
			totalCnt = searchDao.selectByNameTotal(keyword);
		
		//-페이지당 버튼 갯수
		final int pageBtnCount = 5;
		
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
		
		keyMap.put("keyword", keyword);
		keyMap.put("startRnum", startRnum);
		keyMap.put("endRnum", endRnum);
		
		
		List<SearchVo> searchList;
		if("optTitle".equals(kwdOpt)) 
			searchList = searchDao.selectByTitle(keyMap);
		else 
			searchList = searchDao.selectByName(keyMap);
		
		Map <String, Object> pMap = new HashMap<>();
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		pMap.put("searchList", searchList);
	
		return pMap;
		
		
	
	}
	
}
