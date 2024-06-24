package com.cclit.userdemo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cclit.userdemo.bean.User;
import com.cclit.userdemo.bean.UserLoginForm;
import com.cclit.userdemo.exception.PasswordWrongException;

@SpringBootTest
public class UserServiceImplTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void loginSucessfulTest() {
		
		UserLoginForm userLoginForm = new UserLoginForm();
		
		// test the normal function
		userLoginForm.setEmail("galen@cclit.com");
		userLoginForm.setPwd("Lkk8899");
		
		User loginUser = userService.login(userLoginForm);
		
		assertNotNull(loginUser);
		
	}
	
	
	@Test
	public void loginWrongPwsInputTest() {
		
		UserLoginForm userLoginForm = new UserLoginForm();
				
		userLoginForm.setEmail("galen@cclit.com");
		userLoginForm.setPwd("fakePwd");
		
		PasswordWrongException exception  =  assertThrows(PasswordWrongException.class, () -> {
				
			userService.login(userLoginForm);
		
		});
		
		assertEquals("user", exception.getType());
		assertEquals("galen@cclit.com", exception.getAccount());
	}
	
	
	@Test
	public void loginAccountNotFoundTest() {
		
		UserLoginForm userLoginForm = new UserLoginForm();
				
		userLoginForm.setEmail("testtest@test.com");
		userLoginForm.setPwd("fakePwd");
		
		User user = userService.login(userLoginForm);
		
		assertEquals(null, user);
		
	}
	
	
	
}
