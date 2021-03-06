package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;

	// 블로그 정보 가져오기
	public BlogVo getBlogInfo(String id) {

		return blogDao.getBlogInfo(id);
	}

	// 블로그 기본설정 수정
	public void updateBlogInfo(MultipartFile file, BlogVo blogVo) {

		if (file.isEmpty() == false) {

			String saveDir = "C:\\javaStudy\\upload";

			String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
			String filePath = saveDir + "\\" + saveName;

			// ***파일저장(업로드)
			try {
				byte[] fileData = file.getBytes();
				OutputStream out = new FileOutputStream(filePath);
				BufferedOutputStream bout = new BufferedOutputStream(out);

				bout.write(fileData);
				bout.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

			blogVo.setLogoFile(saveName);

		} else {
			BlogVo blogVo2 = getBlogInfo(blogVo.getId());
			blogVo.setLogoFile(blogVo2.getLogoFile());

		}

		blogDao.updateBlogInfo(blogVo);

	}

	// 카테고리 리스트 가져오기
	public List<CategoryVo> getCateList(String id) {

		return blogDao.getCateList(id);
	}

	// 카테고리 추가
	public CategoryVo cateInsert(CategoryVo categoryVo) {

		blogDao.cateInsert(categoryVo);
		int cateNo = categoryVo.getCateNo();

		return blogDao.getCate(cateNo);
	}

	// 카테고리 삭제
	public String cateDelete(int cateNo) {

		// 포스트 값 받아야함
		String result;

		CategoryVo cvo = blogDao.getCate(cateNo);
		int postCnt = cvo.getPostCnt();

		if (postCnt == 0) {
			blogDao.cateDelete(cateNo);
			result = "s";
		} else {

			result = "f";
		}

		return result;
	}

	// 포스트 추가
	public void postInsert(PostVo postVo) {

		blogDao.postInsert(postVo);
		;
	}

	// 포스트 리스트
	public List<PostVo> postList(int cateNo) {

		return blogDao.postList(cateNo);
	}

	// 포스트 한개
	public PostVo getPost(int postNo) {

		return blogDao.getPost(postNo);

	}

}
