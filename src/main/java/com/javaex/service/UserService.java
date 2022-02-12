package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	// 회원가입
	public void join(UserVo userVo) {

		userDao.insert(userVo);

	}

	// 회원가입 아이디 중복체크
	public String idCheck(String id) {
		String result = null;

		List<UserVo> userList = userDao.getIdCheck();
		
		for(int i=0; i<userList.size(); i++) {
			if( userList.get(i).getId() == id) {
				result = "f";
				break;
			}else {
				result = "s";
			}
		}
		return result;
	}

	// 로그인
	public UserVo login(UserVo userVo) {

		UserVo authUser = userDao.selectLogin(userVo);

		return authUser;
	}

}
