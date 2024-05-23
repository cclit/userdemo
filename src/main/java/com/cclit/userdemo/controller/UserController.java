package com.cclit.userdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cclit.userdemo.bean.UserLoginForm;
import com.cclit.userdemo.bean.UserRegisterForm;

import jakarta.validation.Valid;


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
							BindingResult result) {
		
		// check the password check meets the password
		if(!userRegisterForm.getPwd().equals(userRegisterForm.getPwdCheck())) {
			result.rejectValue("pwdCheck", null, "密碼輸入不相符");
		}
		
		
		// work experience check
		if(userRegisterForm.getWorkExperience() != null && userRegisterForm.getWorkExperienceCheck() != null) {
			
			if(userRegisterForm.getWorkExperience() == null) {
				result.rejectValue("workExperience", null,"請填入工作經驗");
			}
			
		} else {
			userRegisterForm.setWorkExperience(null);
		}
		
		
		// Check if the format errors
		if(result.hasErrors()){
			return "registerPage";
		}
		
		
		
		
		return null;
	}
	
	

}
