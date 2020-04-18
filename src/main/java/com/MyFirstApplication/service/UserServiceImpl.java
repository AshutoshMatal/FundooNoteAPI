package com.MyFirstApplication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MyFirstApplication.dto.ForgetPasswordDTO;
import com.MyFirstApplication.dto.UserLoginDTO;
import com.MyFirstApplication.dto.UserRegisterDTO;
import com.MyFirstApplication.model.Response;
import com.MyFirstApplication.model.User;
import com.MyFirstApplication.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;														
	@Autowired
	ResponseImpl responseImpl;
	@Override
	public Response UserLogin(UserLoginDTO userLoginDto) 
	{
		if (userRepository.findByUserName(userLoginDto.getUserName())
				.equals(userRepository.findByPassword(userLoginDto.getPassword())))
		{
			return new Response("Login successfull",200);
		}
		return new Response("already exists",400);
	}
	@Override
	public Response UserRegister(UserRegisterDTO userRegisterDto)throws Exception,NullPointerException 
	{
		User userRegistration=modelMapper.map( userRegisterDto,User.class);
		userRepository.save(userRegistration);

		return new Response("Registration successfull",200);

	}
	@Override
	public Response forgetPassword(ForgetPasswordDTO forgetPasswordDto)
	{
		if (userRepository.findByNickName(forgetPasswordDto.getNickName())
				.equals(userRepository.findByMobileNo(forgetPasswordDto.getMobileNo())))
		{

			return new Response("true", 200);
		}
		return new Response("false", 201);
	}

	@Override
	public Response resetPassword(int id, String password) 
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
