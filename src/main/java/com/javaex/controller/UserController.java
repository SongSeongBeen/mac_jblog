package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@RequestMapping("/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@RequestMapping("join")
	public String join(@ModelAttribute UserVo userVo) {
		userService.join(userVo);
		return "user/joinSuccess";
	}
	
	@RequestMapping("login")
	public String login(@ModelAttribute UserVo userVo,
						HttpSession session) {
		
		UserVo authVo = userService.login(userVo);
		
		if(authVo != null) {
			session.setAttribute("authUser", authVo);
			return "redirect:/";
		}
		else {
			return "redirect:loginForm?result=fail";
		}
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping("dupCheck")
	public boolean dupCheck(@RequestParam("id") String id) {
		return userService.dupCheck(id);
	}
	
	@RequestMapping("initUser")
	public String initUser() {
		userService.initUser();
		return "user/joinSuccess";
	}
	
	
}
