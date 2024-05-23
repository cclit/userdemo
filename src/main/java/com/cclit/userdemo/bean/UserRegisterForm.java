package com.cclit.userdemo.bean;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/*
 *  user register form
 */
public class UserRegisterForm {

	@NotBlank(message = "email不可為空白")
	@Email(message = "輸入格式須為 email")
	private String email;
	
	@NotBlank(message = "密碼不可為空白")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,12}$",message = "密碼格式不符")
	private String pwd;
	
	private String pwdCheck;
	
	@NotBlank(message = "名字不可為空白")
	private String firstName;
	
	@NotBlank(message = "姓氏不可為空白")
	private String lastName;
	
	@NotBlank(message = "暱稱不可為空白")
	private String nickName;
	
	@NotBlank(message = "請選取性別")
	private String gender;
	
	@NotNull(message = "請選擇是否有工作經驗")
	private Boolean workExperienceCheck;
	
	@Digits(integer = 2, fraction = 1, message = "請填入一個整數不超過兩位、小數不超過一位的數字")
	private String workExperience;

	
	/*
	 *  getters and setters
	 */
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

	public String getPwdCheck() {
		return pwdCheck;
	}

	public void setPwdCheck(String pwdCheck) {
		this.pwdCheck = pwdCheck;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Boolean getWorkExperienceCheck() {
		return workExperienceCheck;
	}

	public void setWorkExperienceCheck(Boolean workExperienceCheck) {
		this.workExperienceCheck = workExperienceCheck;
	}

	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}
	
	
	
	
}
