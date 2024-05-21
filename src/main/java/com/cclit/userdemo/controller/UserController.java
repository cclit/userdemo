package com.cclit.userdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cclit.userdemo.bean.UserLoginForm;


/*
 *  user controller
 */
@Controller
public class UserController {
	
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	/*
	 *  show login page
	 */
	@GetMapping("/login")
	public String showLoginPage(Model model) {
		
		model.addAttribute("userLoginForm", new UserLoginForm());
		
		return "loginPage";
	}
	
	
	
	

}
