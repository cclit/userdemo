package com.cclit.userdemo.exception;

/*
 *  PasswordWrongException : user type wrong password during login process 
 */
public class PasswordWrongException extends RuntimeException {

	private String account;
	
	private String type;
	
	public PasswordWrongException() {
		
	}
	
	public PasswordWrongException(String type, String account) {
		
		this.type = type;
		this.account = account;
	}

	
	
	/*
	 *  can only get the type
	 */
	public String getType() {
		return type;
	}
	
	
	/*
	 *  can only get the email
	 */
	public String getAccount() {
		return account;
	}

	
	
	
	
}
