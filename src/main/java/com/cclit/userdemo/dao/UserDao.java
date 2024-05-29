package com.cclit.userdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cclit.userdemo.bean.User;

/**
 *  User repository
 *  
 *  @author GalenLin
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);


}
