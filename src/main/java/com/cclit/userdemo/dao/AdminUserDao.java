package com.cclit.userdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cclit.userdemo.bean.AdminUser;


/**
 *  admin user repository
 *  
 *  @author GalenLin
 */
@Repository
public interface AdminUserDao extends JpaRepository<AdminUser, Long> {

	public AdminUser findByAccount(String account);	
}
