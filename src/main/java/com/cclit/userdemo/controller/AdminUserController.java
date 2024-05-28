package com.cclit.userdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cclit.userdemo.annotation.AdminUserLoginAuthorization;
import com.cclit.userdemo.bean.AdminUser;
import com.cclit.userdemo.bean.AdminUserLoginForm;
import com.cclit.userdemo.service.AdminUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


/**
 *  Admin User Controller
 *  
 *  @author GalenLin
 */
@Controller
public class AdminUserController {
	
	@Autowired
	private AdminUserService adminUserService;
	
	/*
	 *  show admin login page
	 */
	@GetMapping("admin/login")
	public String showAdminLogin(Model model) {
		
		model.addAttribute("adminUserLoginForm", new AdminUserLoginForm());
		
		return "adminLoginPage";
	}
	
	/*
	 *  admin user login
	 */
	@PostMapping("admin/login")
	public String adminUserLogin(@ModelAttribute @Valid AdminUserLoginForm adminUserLoginForm,
								BindingResult result, Model model, HttpServletRequest request) {
		
		// 1. check if the login input info format is wrong
		if(result.hasErrors()) {
			return "adminLoginPage";
		}
		
		// 2. check if the type info is wrong or not
		AdminUser adminUser = adminUserService.login(adminUserLoginForm);
		if(adminUser == null) {
			model.addAttribute("ErrorMsg", "找不到此後台管理員，請註冊或重新登入");
			return "adminLoginPage";
		}
		
		// 3. rewrite sessionID
		request.changeSessionId();
		HttpSession session = request.getSession();
		
		// 4. token setting
		session.setAttribute("adminLogin", adminUser);
		
		return "redirect:/admin/user/arrangement";
	}
	
	/*
	 *  admin user logout
	 */
	@GetMapping("admin/logout")
	public String adminUserLogout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/admin/login";
	}
	
	
	/*
	 *  show admin user arrangement page
	 */
	@GetMapping("admin/user/arrangement")
	@AdminUserLoginAuthorization
	public String showUserArrangementPage() {
		
		return "userArrangementPage";
	}
	

}
