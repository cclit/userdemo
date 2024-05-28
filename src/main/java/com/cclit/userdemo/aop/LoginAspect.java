package com.cclit.userdemo.aop;

import java.io.IOException;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



/**
 * Login aspect
 *  
 * @author GalenLin
 */
@Aspect
@Component
public class LoginAspect {
	
//	@Autowired
//	HttpSession session;

	@Before("@annotation(com.cclit.userdemo.annotation.UserLogin))")
	public void userLogin() {
		
		// get current HttpServletRequest and HttpSession by RequestContextHolder
		HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();
		
		if(request.getSession().getAttribute("login") == null) {
			try {
				
				HttpServletResponse response = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getResponse();
				response.sendRedirect("/user-demo/user/login");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		// get current session by autowired parameter
		
//		if(session.getAttribute("login") == null) {
//			try {
//				
//				HttpServletResponse response = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getResponse();
//				response.sendRedirect("/user-demo/user/login");
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		
	}
	
	@Before("@annotation(com.cclit.userdemo.annotation.AdminUserLoginAuthorization))")
	public void adminUserLogin() {
		// get current HttpServletRequest and HttpSession by RequestContextHolder
		HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();
		
		if(request.getSession().getAttribute("adminLogin") == null) {
			try {
				
				HttpServletResponse response = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getResponse();
				response.sendRedirect("/user-demo/admin/login");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
