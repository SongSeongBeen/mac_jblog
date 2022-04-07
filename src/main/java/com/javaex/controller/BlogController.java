package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "{id}", method = {RequestMethod.GET, RequestMethod.POST})
public class BlogController {
	
	@RequestMapping("")
	public String blogMain(@PathVariable("id")String id,
						   @RequestParam(value = "cateNo", required=false, defaultValue="0")int cateNo,
						   @RequestParam(value = "cateNo", required=false, defaultValue="1")int crtpage,
						   Model model) {
	 return "blog/blog-main";
	}

}
