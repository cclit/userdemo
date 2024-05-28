package com.cclit.userdemo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.cclit.userdemo.bean.User;
import com.cclit.userdemo.bean.UserLoginForm;
import com.cclit.userdemo.bean.UserRegisterForm;
import com.cclit.userdemo.dao.UserDao;
import com.cclit.userdemo.exception.PasswordWrongException;
import com.cclit.userdemo.service.UserService;

/**
 *  user service interface implement class
 *  
 *  @author GalenLin
 */
@Service
public class UserServiceImpl implements UserService {

	private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public Long register(UserRegisterForm userRegisterForm) {
		// 1. check if the email already exits
		User tempUser = userDao.findByEmail(userRegisterForm.getEmail());
		if(tempUser != null) {
			log.warn("該 email {} 已被註冊", userRegisterForm.getEmail());
			return null;
		}
		
		// 2. create the new user account
		User user = new User();
		user.setEmail(userRegisterForm.getEmail());
		
		//// password needed to be encrypted before save to db table
		String hashedPwd = DigestUtils.md5DigestAsHex(userRegisterForm.getPwd().getBytes());
		user.setPwd(hashedPwd);
		user.setFirstName(userRegisterForm.getFirstName());
		user.setLastName(userRegisterForm.getLastName());
		user.setNickName(userRegisterForm.getNickName());
		user.setGender(userRegisterForm.getGender());
		user.setWorkExperience(userRegisterForm.getWorkExperience());
		
		userDao.save(user);
		return user.getUserId();
	}

	@Override
	public User login(UserLoginForm userLoginForm) {
		
		// 1. find the user
		User user = userDao.findByEmail(userLoginForm.getEmail());
		if(user == null) {
			log.warn("無此使用者");
			return null;
		}
		
		// 2. compared the password
		String hashedTypedPwd = DigestUtils.md5DigestAsHex(userLoginForm.getPwd().getBytes());
		if(!hashedTypedPwd.equals(user.getPwd())) {
			log.warn("使用者 {} 登入時密碼輸入錯誤",user.getEmail());
			throw new PasswordWrongException("user",user.getEmail());
		}
		
		return user;
	}

}
