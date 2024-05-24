package com.cclit.userdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cclit.userdemo.bean.UserLoginForm;
import com.cclit.userdemo.bean.UserRegisterForm;
import com.cclit.userdemo.service.UserService;

import jakarta.validation.Valid;


/*
 *  user controller
 */
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	/*
	 *  show login page
	 */
	@GetMapping("user/login")
	public String showLoginPage(Model model) {
		
		model.addAttribute("userLoginForm", new UserLoginForm());
		
		return "loginPage";
	}
	
	/*
	 *  member login
	 */
	@PostMapping("user/login")
	public String login(@ModelAttribute("userLoginForm") @Valid UserLoginForm userLoginForm,
						BindingResult result) {
		
		if(result.hasErrors()) {
			return "loginPage";
		}

		return null;
	}
	
	
	/*
	 *  show registered page
	 */
	@GetMapping("user/register")
	public String showRegisterForm(Model model) {
		
		model.addAttribute("userRegisterForm", new UserRegisterForm());
		
		return "registerPage";
	}
	
	
	/*
	 *  user registered
	 */
	@PostMapping("user/register")
	public String register(@ModelAttribute("userRegisterForm") @Valid UserRegisterForm userRegisterForm,
							BindingResult result, Model model) {
		
		// 1. check the password check meets the password
		if(!userRegisterForm.getPwd().equals(userRegisterForm.getPwdCheck())) {
			result.rejectValue("pwdCheck", null, "密碼輸入不相符");
		}
		
		// 2. work experience check
		if(userRegisterForm.getWorkExperienceCheck() != null) {
			
			if(userRegisterForm.getWorkExperience() == null && userRegisterForm.getWorkExperienceCheck()) {
				result.rejectValue("workExperience", null,"請填入工作經驗");
			}
			
		} else {
			userRegisterForm.setWorkExperience(null);
		}
		
		// 3. Check if the format errors
		if(result.hasErrors()){
			return "registerPage";
		}
		
		// 4. register process : if userId is null means the account already exists
		Long userId = userService.register(userRegisterForm);
		
		if(userId == null) {
			model.addAttribute("accountAreadyExist", "此帳號已經存在");
			return "registerPage";
		}
		
		model.addAttribute("result", "會員註冊成功");
		
		return "registerResult";
	}
	
	

}
