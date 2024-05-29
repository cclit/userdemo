package com.cclit.userdemo.service;

import org.springframework.data.domain.Page;

import com.cclit.userdemo.bean.AdminUserUpdateForm;
import com.cclit.userdemo.bean.User;
import com.cclit.userdemo.bean.UserLoginForm;
import com.cclit.userdemo.bean.UserRegisterForm;

/**
 *  user service interface
 *  
 *  @author GalenLin
 */
public interface UserService {
	
	public Long register(UserRegisterForm userRegisterForm);
	
	public User login(UserLoginForm userLoginForm);
	
	public Page<User> findUserByPage(Integer pageNum);
	
	public User findUserById(Long userId);
	
	public AdminUserUpdateForm getUserUpdateInfo(Long userId);
	
	public Boolean updateUserByAdmin(AdminUserUpdateForm adminUserUpdateForm);
	
}
