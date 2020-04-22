package com.MyFirstApplication.service;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.MyFirstApplication.dto.ForgetPasswordDTO;
import com.MyFirstApplication.dto.UserLoginDTO;
import com.MyFirstApplication.dto.UserRegisterDTO;
import com.MyFirstApplication.model.Response;
import com.MyFirstApplication.model.User;
import com.MyFirstApplication.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {

	User user=new User();
	ModelMapper modelMapper=new ModelMapper();

	

	@Autowired
	private EmailService emailService;

	@Autowired
	UserRepository userRepository;
	@Transactional
	@Override
	public Response login(UserLoginDTO userLoginDto) 
	{
		Optional<User> user=userRepository.findByUsername(userLoginDto.getUsername());

		return new Response("Login successfull",200);

	}
	@Transactional
	@Override
	public Response register(UserRegisterDTO userRegisterDto)throws Exception,NullPointerException
	{
		modelMapper.map(userRegisterDto,user);
		userRepository.save(user);
		return new Response("Registration successfully",200);
	}
	@Transactional
	@Override
	public Response forget(String emailId)
	{

		modelMapper.map(emailId,user);
		Optional<User> optional = userRepository.findByEmailId(emailId);
		if (!optional.isPresent())
		{
			return new Response("false", 201);
		} 
		else 
		{
			User user=optional.get();
			user.setResetToken(UUID.randomUUID().toString());
			userRepository.save(user);
			
			// Email message
			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setFrom("support@demo.com");
			passwordResetEmail.setTo(user.getEmailId());
			passwordResetEmail.setSubject("Password Reset Request");
			passwordResetEmail.setText("To reset your password, click the link below /reset?token=" + user.getResetToken());

			emailService.sendEmail(passwordResetEmail);


			return new Response("true", 200);
		}

	}
	@Transactional
	@Override
	public Response reset(long id, String password) 
	{
		Optional<User> optionalUser=userRepository.findById(id);
		if(optionalUser.isPresent())
		{
			User user=optionalUser.get();
			user.setPassword(password);
			userRepository.save(user);
			return new Response("Successfully done",200);
		}
		return new Response("Failed",400);
	}



}
