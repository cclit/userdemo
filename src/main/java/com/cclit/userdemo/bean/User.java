package com.cclit.userdemo.bean;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/*
 *  user entity
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long userId;
	
	@Column(name = "PWD", length = 50)
	private String pwd;
	
	@Column(name = "FIRST_NAME", length = 30)
	private String firstName;
	
	@Column(name = "LAST_NAME", length = 30)
	private String lastName;
	
	@Column(name = "NICK_NAME", length = 30)
	private String nickName;
	
	@Column(name = "GENDER", length = 8)
	private String gender;
	
	@Column(name = "EMAIL", length = 200)
	private String email;
	
	@CreatedDate
	@Column(name = "REGISTER_TIME", updatable = false)
	private Date registerTime;
	
	@LastModifiedDate
	@Column(name = "UPDATE_TIME")
	private Date updateTime;
	
	@Column(name = "WORK_EXPERIENCE_CHECK")
	private Boolean workExperienceCheck;
	
	@Column(name = "WORK_EXPERIENCE", precision = 3)
	private Double workExperience;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
