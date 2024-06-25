package com.cclit.userdemo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cclit.userdemo.bean.User;
import com.cclit.userdemo.bean.UserLoginForm;
import com.cclit.userdemo.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UserService userService;

	
	@Test
	public void userLoginSucessFulTest() throws Exception {
		
		UserLoginForm userLoginForm = new UserLoginForm();
		userLoginForm.setEmail("galen@cclit.com");
		userLoginForm.setPwd("Lkk8899");
		
		RequestBuilder RequestBuilder = MockMvcRequestBuilders
				.post("/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.flashAttr("userLoginForm", userLoginForm);
				
		
		mockMvc.perform(RequestBuilder)
		.andDo(print())
		.andExpect(status().is(302))
		.andExpect(redirectedUrl("/index"))
		.andExpect(view().name("redirect:/index"));
	}
	
	
	@Test
	public void userLoginFailTest() throws Exception {
		
		UserLoginForm userLoginForm = new UserLoginForm();
		userLoginForm.setEmail("galen@cclit.com");
		userLoginForm.setPwd("1234567"); // wrong pwd
		
		RequestBuilder RequestBuilder = MockMvcRequestBuilders
				.post("/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.flashAttr("userLoginForm", userLoginForm);
		
		mockMvc.perform(RequestBuilder)
		.andDo(print())
		.andExpect(status().is(200))
		.andExpect(view().name("loginPage"));
		
	}
	
	
	
	
	@Test
	public void showMemberCenterWithoutLoginTest() throws Exception {
		
		RequestBuilder RequestBuilder = MockMvcRequestBuilders
			.get("/user/memberCenter");
		
		mockMvc.perform(RequestBuilder)
			.andExpect(status().is(302))
			.andExpect(view().name("memberCenterPage"))
			.andExpect(redirectedUrl("/user-demo/user/login"));
		
	}
	
	

	@Test
	public void showMemberCenterWithLoginTest() throws Exception {
		
		User user = userService.findUserById(Long.valueOf("1"));
		
		RequestBuilder RequestBuilder = MockMvcRequestBuilders
			.get("/user/memberCenter")
			.sessionAttr("login", user);
		
		mockMvc.perform(RequestBuilder)
			.andExpect(status().is(200))
			.andExpect(view().name("memberCenterPage"));
		
	}
	
}
