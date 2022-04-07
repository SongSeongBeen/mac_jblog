package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CommentsService;
import com.javaex.vo.CommentsVo;

@Controller
@RequestMapping("/comments")
public class CommentsController {
	
	@Autowired
	private CommentsService commentsService;
	
	@ResponseBody
	@RequestMapping("/add")
	public CommentsVo add(@ModelAttribute CommentsVo commentsVo) {
		
		return commentsService.add(commentsVo);
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public List<CommentsVo> list(@RequestParam("postNo") int postNo){
		
		return commentsService.getCmtList(postNo);
	}
	
	@ResponseBody
	@RequestMapping("/remove")
	public boolean remove(@RequestParam("cmtNo") int cmtNo) {
		
		return commentsService.remove(cmtNo);
	}
	
	
}
