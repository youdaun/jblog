package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@RequestMapping("/{id}")
	public String blogMain(@PathVariable String id, HttpSession session, Model model) {
		System.out.println("BlogController>blogMain");
		
		model.addAttribute(id);
		
		return "blog/blog-main";
	}
	
	@RequestMapping("/{id}/basic")
	public String adminBasic(@PathVariable String id) {
		System.out.println("BlogController>adminBasic");
		
		return "blog/admin/blog-admin-basic";
	}

}
