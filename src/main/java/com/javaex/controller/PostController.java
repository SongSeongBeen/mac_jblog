package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.PostService;
import com.javaex.vo.PostVo;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;
	
	@ResponseBody
	@RequestMapping("/read")
	public PostVo read(@RequestParam("postNo") int postNo){
		
		return postService.read(postNo);
	}
	
	@RequestMapping("/add")
	public String add(@ModelAttribute PostVo postVo, @RequestParam("id")String id) {
		
		postService.add(postVo);
		
		return "redirect:/"+id+"/admin/writeForm";
	}
	
	
}
