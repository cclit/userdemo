package com.cclit.userdemo.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.cclit.userdemo.bean.User;

@SpringBootTest
public class UserDaoImplTest {
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void findByIdTest() {
		
		Optional<User> op = userDao.findById(Long.valueOf("1"));
		User user = op.get();
		assertNotNull(user);
		assertEquals("Galen", user.getFirstName(), "The last name is wrong!");
		assertNotNull(user.getRegisterTime());
		
	}
	
	@Transactional
	@Test
	public void updateTest() {
		
		Optional<User> op = userDao.findById(Long.valueOf("1"));
		User user = op.get();
		user.setFirstName("Jacky");
		
		userDao.save(user);
		
		Optional<User> opUpdate = userDao.findById(Long.valueOf("1"));
		User userUpdate = opUpdate.get();
		
		assertNotNull(userUpdate);
		assertEquals("Jacky", userUpdate.getFirstName());
		
	}
	
	
	

}
