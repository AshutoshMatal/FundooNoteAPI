package com.fundooNotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Repository;

import com.fundooNotes.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmailId(String emailId);

}