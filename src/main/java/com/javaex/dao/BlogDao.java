package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo getBlogInfo(String id) {
		
		return sqlSession.selectOne("jblog.select-blog", id);
	}
	
	public void updateBlogInfo(BlogVo blogVo) {
		
		sqlSession.update("jblog.update-blog", blogVo);
	}
	
	//카테고리 리스트
	public List<CategoryVo> getCateList(String id) {
		
		return sqlSession.selectList("jblog.select-cateList", id);
	}
	
	//카테고리 1개
	public CategoryVo getCate(int cateNo) {
		
		return sqlSession.selectOne("jblog.select-cate", cateNo);
	}
	
	//카테고리 추가
	public void cateInsert(CategoryVo categoryVo) {
		
		sqlSession.insert("jblog.cate-insert", categoryVo);
		
	}
	
	//카테고리 삭제
	public void cateDelete(int cateNo) {
		
		sqlSession.delete("jblog.delete-cate", cateNo);
	}
	
	//포스트 추가
	public void postInsert(PostVo postVo) {
		
		sqlSession.insert("jblog.post-insert", postVo);
	}
	
	//포스트 리스트 
	public List<PostVo> postList(int cateNo) {
		
		return sqlSession.selectList("jblog.select-postList", cateNo);
	}
	
	//포스트 한개
	public PostVo getPost(int postNo) {
		
		return sqlSession.selectOne("jblog.select-post", postNo);
		
	}

}
