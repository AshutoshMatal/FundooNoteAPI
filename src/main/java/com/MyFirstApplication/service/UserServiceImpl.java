package com.MyFirstApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MyFirstApplication.dto.ForgetPasswordDTO;
import com.MyFirstApplication.dto.UserLoginDTO;
import com.MyFirstApplication.dto.UserRegisterDTO;
import com.MyFirstApplication.model.Response;
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
	public Response UserRegister(UserRegisterDTO userRegisterDto) throws Exception, NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response forgetPassword(ForgetPasswordDTO forgetPasswordDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response resetPassword(int id, String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
