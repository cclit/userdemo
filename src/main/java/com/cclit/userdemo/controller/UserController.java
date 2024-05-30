package com.cclit.userdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cclit.userdemo.annotation.UserLogin;
import com.cclit.userdemo.bean.User;
import com.cclit.userdemo.bean.UserLoginForm;
import com.cclit.userdemo.bean.UserRegisterForm;
import com.cclit.userdemo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
	 *  user login
	 */
	@PostMapping("user/login")
	public String login(@ModelAttribute("userLoginForm") @Valid UserLoginForm userLoginForm,
						BindingResult result, Model model, HttpServletRequest request) {
		
		// 1. check if the login input info format is wrong
		if(result.hasErrors()) {
			return "loginPage";
		}

		// 2. check if the user is already register
		User user = userService.login(userLoginForm);
		if(user == null) {
			model.addAttribute("ErrorMsg", "找不到使用者，請註冊或重新登入");
			return "loginPage";
		}
		
		// 3. rewrite sessionID
		request.changeSessionId();
		HttpSession session = request.getSession();
		
		// 4. token setting
		session.setAttribute("login", user);
		
		return "redirect:/index";
	}
	
	
	/*
	 *  user logout
	 */
	@GetMapping("user/logout")
	public String logout(HttpServletRequest request) {
		
		request.getSession().invalidate();
		
		return "redirect:/index";
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
			
			// work experience check : true, the workExperience must be filled
			if(userRegisterForm.getWorkExperienceCheck() && userRegisterForm.getWorkExperience() == null) {
				result.rejectValue("workExperience", null,"請填入工作經驗");
			}
			
			if(!userRegisterForm.getWorkExperienceCheck()) {
				userRegisterForm.setWorkExperience(null);
			}
			
		} else {
			userRegisterForm.setWorkExperienceCheck(false);
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
	
	@UserLogin
	@GetMapping("user/memberCenter")
	public String showMemberCenter(HttpServletRequest request, Model model) {
		
		// check if the member is login
//		if(request.getSession().getAttribute("login") == null) {
//			return "redirect:/user/login";
//		}
		
		return "memberCenterPage";
	}
	

}
