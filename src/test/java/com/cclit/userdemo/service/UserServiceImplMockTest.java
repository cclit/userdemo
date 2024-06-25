package com.cclit.userdemo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cclit.userdemo.bean.User;
import com.cclit.userdemo.dao.UserDao;

@SpringBootTest
public class UserServiceImplMockTest {

	@Autowired
	private UserService userService;
	
	@MockBean
	private UserDao userDao;
	
	@Test
	public void findUserByIdTest() {
		
		User mockUser = new User();
		mockUser.setEmail("test@test.com");
		mockUser.setUserId(Long.valueOf("100"));
		mockUser.setPwd("123456");
		mockUser.setFirstName("Jay");
		mockUser.setLastName("Hug");
		mockUser.setGender("male");
		
		Optional<User> mockOpUser = Optional.of(mockUser);
		
		Mockito.when(userDao.findById(Mockito.any())).thenReturn(mockOpUser);
		
		User user = userService.findUserById(Long.valueOf("1"));
		
		assertNotNull(user);
		assertEquals(Long.valueOf("100"), user.getUserId());
		assertEquals("Jay", user.getFirstName());
		
	}
	
	
	
	
	
	
	
	
}
