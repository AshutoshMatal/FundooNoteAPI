package com.MyFirstApplication.service;
import java.util.Optional;

import org.modelmapper.ModelMapper;
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
	User user=new User();
	ModelMapper modelMapper=new ModelMapper();
	@Autowired
	UserRepository userRepository;
	@Override
	public Response login(UserLoginDTO userLoginDto) 
	{
		Optional<User> optionalUser=userRepository.findByUsername(userLoginDto.getUsername());

		return new Response("Login successfull",200);

	}
	@Override
	public Response register(UserRegisterDTO userRegisterDto)throws Exception
	{
		modelMapper.map( userRegisterDto,user);
		userRepository.save(user);
		if (userRepository.findByUsername(user.getUsername())
				.equals(userRepository.findByPassword(user.getPassword())))
		{

			return new Response("Registration successfull",200);

		}
		return new Response("Error",400);
	}
	@Override
	public Response forget(ForgetPasswordDTO forgetPasswordDto)
	{

		modelMapper.map(forgetPasswordDto, user);

		// Checking secret information is valid or not
		if (userRepository.findByEmailId(user.getEmailId())
				.equals(userRepository.findByMobileNo(user.getMobileNo())))
		{


			return new Response("true", 200);
		}
		return new Response("false", 201);
	}


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
