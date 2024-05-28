package com.cclit.userdemo.service;

import com.cclit.userdemo.bean.AdminUser;
import com.cclit.userdemo.bean.AdminUserLoginForm;

/**
 *  Admin User Service - Interface
 *  
 *  @author GalenLin
 */
public interface AdminUserService {

	
	public AdminUser login(AdminUserLoginForm adminUserLoginForm);
	
}
