package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/joinForm")
	public String joinForm() {
		System.out.println("UserController>joinForm");
		
		return "user/joinForm";
	}
	
	@RequestMapping("/join")
	public String join(UserVo userVo) {
		System.out.println("UserController>join");

		userService.join(userVo);
		
		return "blog/blog-main";
	}
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		System.out.println("UserController>loginForm");
		
		return "user/loginForm";
	}
	
	@RequestMapping("/login")
	public String login(UserVo userVo, HttpSession session) {
		System.out.println("UserController>login");
		
		UserVo authUser = userService.login(userVo);
		
		if(authUser != null) { //로그인 성공
			session.setAttribute("authUser", authUser);
			
			return "blog/blog-main";
		}else { //로그인 실패
			
			return "user/loginForm";
		}
		
	}
	
	

}
