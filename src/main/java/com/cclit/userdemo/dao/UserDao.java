package com.cclit.userdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cclit.userdemo.bean.User;

public interface UserDao extends JpaRepository<User, Long> {

}
