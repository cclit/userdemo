package com.cclit.userdemo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cclit.userdemo.bean.AdminUserLoginForm;
import com.cclit.userdemo.bean.UserLoginForm;
import com.cclit.userdemo.exception.EmptyObjectException;
import com.cclit.userdemo.exception.PasswordWrongException;
import com.cclit.userdemo.exception.UserNotFoundException;

/*
 *  System exception handler
 */
@ControllerAdvice
public class UserDemoExceptionHandler {

	@ExceptionHandler(PasswordWrongException.class)
	public String PasswordWrongExceptionHandler(PasswordWrongException exception, Model model) {
		
		if(exception.getType().equals("user")) {
			UserLoginForm userLoginForm = new UserLoginForm();		
			userLoginForm.setEmail(exception.getAccount());
			
			model.addAttribute("userLoginForm", userLoginForm);
			model.addAttribute("ErrorMsg", "密碼錯誤，請重新輸入");
			
			return "loginPage";
			
		}
		
		if(exception.getType().equals("adminUser")) {
			AdminUserLoginForm adminUserLoginForm = new AdminUserLoginForm();
			adminUserLoginForm.setAccount(exception.getAccount());
			
			model.addAttribute("adminUserLoginForm", adminUserLoginForm);
			model.addAttribute("ErrorMsg", "密碼錯誤，請重新輸入");
			
			return "adminLoginPage";
		}
		
		return null;
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public String UserNotFoundExceptionHandler(UserNotFoundException exception, Model model) {
		
		model.addAttribute("updateInfo", "找不到該使用者");
		
		return "adminUserDetailPage";
	}
	
	
	public String EmptyObjectExceptionHandler(EmptyObjectException exception, RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addFlashAttribute("errorMsg", "查詢結果有誤，請重新查詢");
		
		return "redirect:/admin/user/arrangement";
	}
	
	
}
