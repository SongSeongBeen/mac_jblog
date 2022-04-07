package com.javaex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.SearchService;

@Controller
public class MainController {
	
	@Autowired
	private SearchService searchService;

	@RequestMapping("/")
	public String main() {
		
		return "main/index";
	}

	@RequestMapping("/search")
	public String search(Model model,
						 @RequestParam(value = "keyword", required=false,  defaultValue="") String keyword,
						 @RequestParam(value = "kwdOpt", required=false, defaultValue="optTitle") String kwdOpt,
						 @RequestParam(value = "crtPage", required=false, defaultValue="1") int crtPage){
		Map<String, Object> pMap = searchService.Search(keyword, kwdOpt, crtPage);
		model.addAttribute("pMap",pMap);
		
		return "main/index";
	}
	

}
