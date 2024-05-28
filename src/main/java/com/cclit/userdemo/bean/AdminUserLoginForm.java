package com.cclit.userdemo.bean;

import jakarta.validation.constraints.NotBlank;

/**
 *  admin user login form
 *  
 *  @author GalenLin
 */
public class AdminUserLoginForm {
	
	@NotBlank(message = "請輸入系統管理員帳號")
	private String account;
	
	@NotBlank(message = "請輸入密碼")
	private String pwd;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	
	

}
