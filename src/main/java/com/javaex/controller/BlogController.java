package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/{id}")
	public String blogMain(@PathVariable String id, HttpSession session, Model model) {
		System.out.println("BlogController>blogMain");
		
		BlogVo blogVo = blogService.getBlogInfo(id);
		model.addAttribute("blogVo", blogVo);
		
		return "blog/blog-main";
	}
	
	@RequestMapping("/{id}/basic")
	public String adminBasic(@PathVariable String id, Model model) {
		System.out.println("BlogController>adminBasic");
		
		BlogVo blogVo = blogService.getBlogInfo(id);
		model.addAttribute("blogVo", blogVo);
		
		return "blog/admin/blog-admin-basic";
	}
	
	@RequestMapping("/{id}/update")
	public String updateBolgInfo(@PathVariable String id, @RequestParam("file") MultipartFile file, BlogVo blogVo) {
		System.out.println("BlogController>updateBolgInfo");
		
		blogService.updateBlogInfo(file, blogVo);
		
		return "rediect:/{id}/basic";
	}

}
