package com.cclit.userdemo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cclit.userdemo.bean.User;

/**
 *  User repository
 *  
 *  @author GalenLin
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);
	
	@Query("from User u where u.email like %:keyword%")
	public Page<User> findUsersByAccountKeyword(@Param(value = "keyword") String keyword, Pageable pgb);
	
	@Query("from User u where u.firstName like %:keyword% or u.lastName like %:keyword%")
	public Page<User> findUsersByNameKeyword(@Param(value = "keyword") String keyword, Pageable pgb);

	@Query("from User u where u.email like %:accountKeyword% and "
			+ "u.nickName like %:nickNameKeyword% and u.gender = :gender")
	public Page<User> findUsersByAccountAdnNickNameAndGender(@Param(value = "accountKeyword")String accountKeyword,
															 @Param(value = "nickNameKeyword")String nickNameKeyword,
															 @Param(value = "gender")String gender,
															 Pageable pgb);

}
