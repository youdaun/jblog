package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlsession;
	
	public void insert(UserVo userVo) {
		
		sqlsession.insert("jblog.join-users", userVo);
		
		BlogVo blogVo = new BlogVo();
		
		blogVo.setId(userVo.getId());
		blogVo.setBlogTitle(userVo.getUserName() + "의 블로그 입니다.");
		blogVo.setLogoFile("spring-logo.jpg");
		
		sqlsession.insert("jblog.join-blog", blogVo);
	}
	
	public UserVo selectLogin(UserVo userVo) {
		
		UserVo authUser = sqlsession.selectOne("jblog.login", userVo);
		
		return authUser;
	}
	
	//아이디 중복체크
	public List<UserVo> getIdCheck() {
		
		List<UserVo> userList = sqlsession.selectList("jblog.idCheck");
		
		return userList;
	}

}
