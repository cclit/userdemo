package com.cclit.userdemo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cclit.userdemo.bean.UserLoginForm;
import com.cclit.userdemo.exception.PasswordWrongException;

/*
 *  System exception handler
 */
@ControllerAdvice
public class UserDemoExceptionHandler {

	@ExceptionHandler(PasswordWrongException.class)
	public String PasswordWrongExceptionHandler(PasswordWrongException exception, Model model) {
		UserLoginForm userLoginForm = new UserLoginForm();		
		userLoginForm.setEmail(exception.getUserEmail());
		
		model.addAttribute("userLoginForm", userLoginForm);
		model.addAttribute("ErrorMsg", "密碼錯誤，請重新輸入");
		
		return "loginPage";
	}
	
}
