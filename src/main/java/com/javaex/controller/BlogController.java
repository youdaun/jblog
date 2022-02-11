package com.javaex.controller;

import java.util.List;

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
	public String blogMain(@PathVariable String id, Model model, @RequestParam("cateNo") int cateNo, @RequestParam("postNo") int postNo) {
		System.out.println("BlogController>blogMain");

		BlogVo blogVo = blogService.getBlogInfo(id);
		model.addAttribute("blogVo", blogVo);
		
		List<CategoryVo> cateList = blogService.getCateList(id);
		model.addAttribute("cateList", cateList);
		
		if(cateNo == 0) {
			cateNo = cateList.get(0).getCateNo();
		}
		
		List<PostVo> postList = blogService.postList(cateNo);
		model.addAttribute("postList", postList);
		
		if(postNo == 0 && postList.size() != 0) {
			postNo = postList.get(0).getPostNo();
			
			PostVo postVo = blogService.getPost(postNo);
			model.addAttribute("postVo", postVo);
			
		}else if(postNo != 0) {
			
			PostVo postVo = blogService.getPost(postNo);
			model.addAttribute("postVo", postVo);
		}else {
			System.out.println("포스트 없음");
		}
	
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
	
	//카테고리 리스트
	@ResponseBody
	@RequestMapping("/category/list")
	public List<CategoryVo> cateList(Model model, String id){
		System.out.println("BlogController>cateList");
		
		List<CategoryVo> cateList = blogService.getCateList(id);
		
		return cateList;
	}

	//카테고리 추가
	@ResponseBody
	@RequestMapping("/category/add")
	public CategoryVo cateAdd(CategoryVo categoryVo) {
		System.out.println("BlogController>cateAdd");
		
		CategoryVo cvo = blogService.cateInsert(categoryVo);
		
		return cvo;
		
	}
	
	//카테고리 삭제
	@ResponseBody
	@RequestMapping("/category/delete")
	public String cateDelete(int cateNo) {
		System.out.println("BlogController>cateDelete");
		
		String result = blogService.cateDelete(cateNo);
		
		return result;
		
	}
	
	@RequestMapping("/{id}/admin/writeForm")
	public String writeForm(@PathVariable String id, Model model) {
		System.out.println("BlogController>writeForm");
	
		BlogVo blogVo = blogService.getBlogInfo(id);
		model.addAttribute("blogVo", blogVo);
		
		List<CategoryVo> cateList = blogService.getCateList(id);
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
