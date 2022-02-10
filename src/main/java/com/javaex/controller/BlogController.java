package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

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
	
	@RequestMapping("/{id}/admin/basic")
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
		
		return "redirect:/"+id+"/admin/basic";
	}
	
	@RequestMapping("/{id}/admin/category")
	public String category(@PathVariable String id, Model model) {
		System.out.println("BlogController>category");
		
		BlogVo blogVo = blogService.getBlogInfo(id);
		model.addAttribute("blogVo", blogVo);

		return "blog/admin/blog-admin-cate";
	}
	
	@ResponseBody
	@RequestMapping("/category/list")
	public List<CategoryVo> cateList(Model model){
		System.out.println("BlogController>cateList");
		
		List<CategoryVo> cateList = blogService.getCateList();
		
		return cateList;
	}

	@ResponseBody
	@RequestMapping("/category/add")
	public CategoryVo cateAdd(CategoryVo categoryVo) {
		System.out.println("BlogController>cateAdd");
		
		CategoryVo cvo = blogService.cateInsert(categoryVo);
		System.out.println(cvo);
		
		return cvo;
		
	}
	
	@RequestMapping("/{id}/admin/writeForm")
	public String writeForm(@PathVariable String id, Model model) {
		System.out.println("BlogController>writeForm");
	
		BlogVo blogVo = blogService.getBlogInfo(id);
		model.addAttribute("blogVo", blogVo);
		
		List<CategoryVo> cateList = blogService.getCateList();
		model.addAttribute("cateList", cateList);

		return "blog/admin/blog-admin-write";
	}
	
	@RequestMapping("/{id}/admin/write")
	public String writePost(@PathVariable String id, PostVo postVo) {
		System.out.println("BlogController>writePost");
		
		System.out.println(postVo);
		blogService.postInsert(postVo);
		
		return "redirect:/"+ id +"/admin/writeForm";
	}

}
