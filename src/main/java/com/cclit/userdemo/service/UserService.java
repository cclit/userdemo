package com.cclit.userdemo.service;

import com.cclit.userdemo.bean.User;
import com.cclit.userdemo.bean.UserLoginForm;
import com.cclit.userdemo.bean.UserRegisterForm;

/*
 *  user service interface
 */
public interface UserService {
	
	public Long register(UserRegisterForm userRegisterForm);
	
	public User login(UserLoginForm userLoginForm);
	
}
