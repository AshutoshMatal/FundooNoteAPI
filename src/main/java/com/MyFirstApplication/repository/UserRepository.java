package com.MyFirstApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.MyFirstApplication.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findByUserName(String userName);

	List<User> findByPassword(String password);
	
	List<User> findByEmailId(String emailId);
	
	List<User> findByNickName(String nickName);
	
	List<User> findByMobileNo(long MobileNo);
	
	List<User> findByResetPassword(int id,String password);
	
	
}