package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	//회원가입
	public String join(UserVo userVo) {
		
		String result;
		
		//아직 미구현
		if(userVo.getId() == null) {
			result = "f";
		}else {
			result = "s";
			
			userDao.insert(userVo);
		}	
		
		return result;
	}
	
	//로그인
	public UserVo login(UserVo userVo) {
		
		UserVo authUser = userDao.selectLogin(userVo);
		
		return authUser;
	}

}
