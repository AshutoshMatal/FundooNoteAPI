package com.MyFirstApplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.MyFirstApplication.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Optional<User> findByPassword(String password);

	Optional<User> findByEmailId(String emailId);

	Optional<User> findByMobileNo(long  mobileNo);
	
	//Optional<User> findByEmailIdIgnoreCase(String emailId);

}