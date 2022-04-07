package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CommentsDao;
import com.javaex.vo.CommentsVo;

@Service
public class CommentsService {

	@Autowired
	private CommentsDao commentsDao;

	public CommentsVo add(CommentsVo commentsVo) {
		commentsDao.insertSelectNo(commentsVo);

		int cmtNo = commentsVo.getCmtNo();
		return commentsDao.selectComment(cmtNo);
	}

	public List<CommentsVo> getCmtList(int postNo) {
		return commentsDao.selectCmtList(postNo);
	}

	public boolean remove(int cmtNo) {
		int count = commentsDao.deleteCmt(cmtNo);
		if(count > 0)
			return true;
		else
			return false;
	}
}
