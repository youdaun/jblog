package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;

	public BlogVo getBlogInfo(String id) {

		return blogDao.getBlogInfo(id);
	}

	public void updateBlogInfo(MultipartFile file, BlogVo blogVo) {
		
		if(file.isEmpty() == false) {
			
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
			
		}else {			
			BlogVo blogVo2 = getBlogInfo(blogVo.getId());
			blogVo.setLogoFile(blogVo2.getLogoFile());

		}

		blogDao.updateBlogInfo(blogVo);
		
	}

}
