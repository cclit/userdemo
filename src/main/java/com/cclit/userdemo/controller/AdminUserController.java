package com.cclit.userdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cclit.userdemo.annotation.AdminUserLoginAuthorization;
import com.cclit.userdemo.bean.AdminUser;
import com.cclit.userdemo.bean.AdminUserLoginForm;
import com.cclit.userdemo.bean.AdminUserUpdateForm;
import com.cclit.userdemo.bean.User;
import com.cclit.userdemo.service.AdminUserService;
import com.cclit.userdemo.service.UserService;

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
	
	@Autowired
	private UserService userService;
	
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
	public String showUserArrangementPage(Model model, @RequestParam(name="p", defaultValue = "1" ) Integer pageNum) {

		Page<User> pageUsers = userService.findUserByPage(pageNum);
		
		model.addAttribute("page", pageUsers);

		return "userArrangementPage";
	}
	
	
	/*
	 *  show user detail page
	 */
	@GetMapping("admin/user/detail/{uid}")
	@AdminUserLoginAuthorization
	public String showUserDetailPage(@PathVariable Long uid, Model model) {
		
		User user = userService.findUserById(uid);
		
		model.addAttribute("user", user);
		
		return "adminUserDetailPage";
	}
	
	
	/*
	 *  show user update page
	 */
	@GetMapping("admin/user/update/{uid}")
	@AdminUserLoginAuthorization
	public String showUserUpdatePage(@PathVariable Long uid, Model model) {
		
		AdminUserUpdateForm adminUserUpdateForm = userService.getUserUpdateInfo(uid);
		
		model.addAttribute("userUpdateInfo", adminUserUpdateForm);
		
		return "adminUserUpdatePage";
	}
	
	
	
	/*
	 *  update user information
	 */
	@PostMapping("admin/user/update/{uid}")
	@AdminUserLoginAuthorization
	public String userUpdate(@PathVariable Long uid, @ModelAttribute("userUpdateInfo") 
							@Valid AdminUserUpdateForm adminUserUpdateForm,
							BindingResult result,
							RedirectAttributes redirectAttributes,
							Model model) {
		
		// 1. work experience fill check
		if(adminUserUpdateForm.getWorkExperienceCheck() != null) {
			
			if(adminUserUpdateForm.getWorkExperience() == null && adminUserUpdateForm.getWorkExperienceCheck()) {
				result.rejectValue("workExperience", null,"請填入工作經驗");
			}
			
		} else {
			adminUserUpdateForm.setWorkExperience(null);
		}
		
		// 2. form fill error check
		if(result.hasErrors()) {
			return "adminUserUpdatePage";
		}
		
		
		// 3. update user information
		//// update sucess!
		if(userService.updateUserByAdmin(adminUserUpdateForm)) {
			redirectAttributes.addFlashAttribute("updateInfo", "會員資訊更新成功!");
			String url = "redirect:/admin/user/detail/" + uid;
			return url;
		}
		
		//// update fail
		model.addAttribute("updateInfo", "會員資訊更新失敗，找不到原有的會員資訊");		
		return "adminUserUpdatePage";
	}
	
	
	
	

	
	
}
