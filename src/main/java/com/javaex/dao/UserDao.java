package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlsession;
	
	public void insert(UserVo userVo) {
		
		sqlsession.insert("jblog.join-users", userVo);
		sqlsession.insert("jblog.join-blog", userVo);
	}
	
	public UserVo selectLogin(UserVo userVo) {
		
		UserVo authUser = sqlsession.selectOne("jblog.login", userVo);
		
		return authUser;
	}

}
