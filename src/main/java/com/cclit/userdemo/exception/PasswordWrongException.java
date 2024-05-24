package com.cclit.userdemo.exception;

/*
 *  PasswordWrongException : user type wrong password during login process 
 */
public class PasswordWrongException extends RuntimeException {

	private String userEmail;
	
	public PasswordWrongException() {
		
	}
	
	public PasswordWrongException(String userEmail) {
		
		this.userEmail = userEmail;
	}

	
	/*
	 *  can only get the email
	 */
	public String getUserEmail() {
		return userEmail;
	}

	
	
	
}
