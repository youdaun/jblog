package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

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

}
