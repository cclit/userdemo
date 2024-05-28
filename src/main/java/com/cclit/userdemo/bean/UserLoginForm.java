package com.cclit.userdemo.bean;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 *  user login form
 *  
 *  @author GalenLin
 */
public class UserLoginForm {

	@NotBlank(message = "帳號不可為空")
	@Email(message = "請輸入註冊用 email")
	private String email;
	
	@NotBlank(message = "密碼不可為空白")
	private String pwd;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
}
