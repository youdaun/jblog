package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/{id}")
	public String blogMain(@PathVariable String id, HttpSession session, Model model) {
		System.out.println("BlogController>blogMain");
		
		model.addAttribute(id);
		
		return "blog/blog-main";
	}
	
	@RequestMapping("/{id}/basic")
	public String adminBasic(@PathVariable String id, Model model) {
		System.out.println("BlogController>adminBasic");
		
		BlogVo blogVo = blogService.getBlogInfo(id);
		model.addAttribute("blogVo", blogVo);
		
		return "blog/admin/blog-admin-basic";
	}

}
