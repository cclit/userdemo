package com.cclit.userdemo.bean;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *  Admin User update form - used for user info update by admin
 *  
 *  @author GalenLin
 */
public class AdminUserUpdateForm {
	
	/*
	 *  usrId could not be changed
	 */
	private Long userId;
	
	/*
	 *  email could not be changed
	 */
	private String email;
	
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
	private Double workExperience;
	
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setEmail(String email) {
		this.email = email;
	}
		
	public String getEmail() {
		return email;
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

	public Double getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(Double workExperience) {
		this.workExperience = workExperience;
	}

	
}
