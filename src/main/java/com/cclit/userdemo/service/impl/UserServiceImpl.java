package com.cclit.userdemo.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.cclit.userdemo.bean.AdminUserUpdateForm;
import com.cclit.userdemo.bean.User;
import com.cclit.userdemo.bean.UserLoginForm;
import com.cclit.userdemo.bean.UserRegisterForm;
import com.cclit.userdemo.dao.UserDao;
import com.cclit.userdemo.exception.PasswordWrongException;
import com.cclit.userdemo.exception.UserNotFoundException;
import com.cclit.userdemo.service.UserService;

/**
 *  user service interface implement class
 *  
 *  @author GalenLin
 */
@Service
public class UserServiceImpl implements UserService {

	private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public Long register(UserRegisterForm userRegisterForm) {
		// 1. check if the email already exits
		User tempUser = userDao.findByEmail(userRegisterForm.getEmail());
		if(tempUser != null) {
			log.warn("該 email {} 已被註冊", userRegisterForm.getEmail());
			return null;
		}
		
		// 2. create the new user account
		User user = new User();
		user.setEmail(userRegisterForm.getEmail());
		
		//// password needed to be encrypted before save to db table
		String hashedPwd = DigestUtils.md5DigestAsHex(userRegisterForm.getPwd().getBytes());
		user.setPwd(hashedPwd);
		user.setFirstName(userRegisterForm.getFirstName());
		user.setLastName(userRegisterForm.getLastName());
		user.setNickName(userRegisterForm.getNickName());
		user.setGender(userRegisterForm.getGender());
		user.setWorkExperienceCheck(userRegisterForm.getWorkExperienceCheck());
		user.setWorkExperience(userRegisterForm.getWorkExperience());
		
		userDao.save(user);
		return user.getUserId();
	}

	@Override
	public User login(UserLoginForm userLoginForm) {
		
		// 1. find the user
		User user = userDao.findByEmail(userLoginForm.getEmail());
		if(user == null) {
			log.warn("無此使用者");
			return null;
		}
		
		// 2. compared the password
		String hashedTypedPwd = DigestUtils.md5DigestAsHex(userLoginForm.getPwd().getBytes());
		if(!hashedTypedPwd.equals(user.getPwd())) {
			log.warn("使用者 {} 登入時密碼輸入錯誤",user.getEmail());
			throw new PasswordWrongException("user",user.getEmail());
		}
		
		return user;
	}

	@Override
	public Page<User> findUserByPage(Integer pageNum) {
		
		Pageable pbg =  PageRequest.of(pageNum - 1, 5, Sort.Direction.DESC, "userId");
		
		Page<User> page = userDao.findAll(pbg);
		
		return page;
	}

	@Override
	public User findUserById(Long userId) {
		
		 Optional<User> opUser = userDao.findById(userId);
		 
		 if(opUser.isEmpty()) {
			 throw new UserNotFoundException();
		 }		 
		
		return opUser.get();
	}

	@Override
	public AdminUserUpdateForm getUserUpdateInfo(Long userId) {
		
		User user = findUserById(userId);
		
		AdminUserUpdateForm userUpdateInfo = new AdminUserUpdateForm();
		
		userUpdateInfo.setUserId(user.getUserId());
		userUpdateInfo.setEmail(user.getEmail());
		userUpdateInfo.setFirstName(user.getFirstName());
		userUpdateInfo.setLastName(user.getLastName());
		userUpdateInfo.setNickName(user.getNickName());
		userUpdateInfo.setGender(user.getGender());
		userUpdateInfo.setWorkExperienceCheck(user.getWorkExperienceCheck());
		userUpdateInfo.setWorkExperience(user.getWorkExperience());
		
		return userUpdateInfo;
	}

	@Override
	public Boolean updateUserByAdmin(AdminUserUpdateForm adminUserUpdateForm) {
		
		User user = findUserById(adminUserUpdateForm.getUserId());
		
		user.setFirstName(adminUserUpdateForm.getFirstName());
		user.setLastName(adminUserUpdateForm.getLastName());
		user.setNickName(adminUserUpdateForm.getNickName());
		user.setGender(adminUserUpdateForm.getGender());
		user.setWorkExperienceCheck(adminUserUpdateForm.getWorkExperienceCheck());
		user.setWorkExperience(adminUserUpdateForm.getWorkExperience());
		
		User updateUser = userDao.save(user);
		
		if(updateUser == null) {
			return false;
		}
		
		return true;
	}

	@Override
	public Page<User> findUsersByAccountKeyword(Integer pageNum, String keyword) {

		Pageable pbg = PageRequest.of(pageNum - 1, 5, Sort.Direction.DESC, "userId");
		
		Page<User> page = userDao.findUsersByAccountKeyword(keyword, pbg);
		
		return page;
	}

	@Override
	public Page<User> findUsersByNameKeyword(Integer pageNum, String keyword) {
		
		Pageable pbg = PageRequest.of(pageNum - 1, 5, Sort.Direction.DESC, "userId");
		
		Page<User> page = userDao.findUsersByNameKeyword(keyword, pbg);
		
		return page;
	}

	@Override
	public Page<User> findUsersByAccountAndNickNameAndGender(Integer pageNum, 
															 String accountKeyword,
															 String nickNameKeyword, 
															 String gender) {
		
		Pageable pbg = PageRequest.of(pageNum - 1, 5, Sort.Direction.DESC, "userId");
		
		Page<User> page = userDao.findUsersByAccountAdnNickNameAndGender(accountKeyword, nickNameKeyword, gender, pbg);
		
		return page;
	}

	@Override
	public void deleteUserById(Long userId) {
		
		userDao.deleteById(userId);
		
	}


}
