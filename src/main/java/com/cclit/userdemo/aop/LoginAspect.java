package com.cclit.userdemo.aop;

import java.io.IOException;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



/*
 *  Login aspect
 */
@Aspect
@Component
public class LoginAspect {

	@Before("@annotation(com.cclit.userdemo.annotation.UserLogin))")
	public void userLogin() {
		
		HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();
		
		if(request.getSession().getAttribute("login") == null) {
			try {
				
				HttpServletResponse response = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getResponse();
				response.sendRedirect("/user-demo/user/login");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		
	}
	
	
}
