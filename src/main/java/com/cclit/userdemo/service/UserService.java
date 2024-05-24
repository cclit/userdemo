package com.cclit.userdemo.service;

import com.cclit.userdemo.bean.UserRegisterForm;

/*
 *  user service interface
 */
public interface UserService {
	
	public Long register(UserRegisterForm userRegisterForm);
	
}
