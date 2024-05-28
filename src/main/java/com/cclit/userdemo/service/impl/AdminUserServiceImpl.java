package com.cclit.userdemo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.cclit.userdemo.bean.AdminUser;
import com.cclit.userdemo.bean.AdminUserLoginForm;
import com.cclit.userdemo.config.UserDemoProps;
import com.cclit.userdemo.dao.AdminUserDao;
import com.cclit.userdemo.exception.PasswordWrongException;
import com.cclit.userdemo.service.AdminUserService;

import jakarta.annotation.PostConstruct;


/**
 *  Admin User Service - implement class
 *  
 *  @author GalenLin
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
	
	private final static Logger log = LoggerFactory.getLogger(AdminUserServiceImpl.class);
	
	@Autowired
	private AdminUserDao adminUserDao;
	
	@Autowired
	private UserDemoProps userDemoProps;
	
	/*
	 *  default admin user initiation
	 */
	@PostConstruct
	private void initData() {
		
		if(userDemoProps.getEnableInit()) {
			if(adminUserDao.findByAccount("ADMIN") == null) {				
				AdminUser adminUser = new AdminUser();
				adminUser.setAccount("ADMIN");
				adminUser.setPwd(DigestUtils.md5DigestAsHex("123456".getBytes()));
				adminUserDao.save(adminUser);
			}
		}
		
	}

	@Override
	public AdminUser login(AdminUserLoginForm adminUserLoginForm) {
		
		// 1. find the user
		AdminUser user = adminUserDao.findByAccount(adminUserLoginForm.getAccount());
		if(user == null) {
			log.warn("無該 account {} 使用者", adminUserLoginForm.getAccount());
			return null;
		}
		
		// 2. compared the password
		String hashedPwd = DigestUtils.md5DigestAsHex(adminUserLoginForm.getPwd().getBytes());
		if(!hashedPwd.equals(user.getPwd())) {
			log.warn("使用者 {} 登入時密碼輸入錯誤",user.getAccount());
			throw new PasswordWrongException("adminUser",user.getAccount());
		}
		
		return user;
	}

}
