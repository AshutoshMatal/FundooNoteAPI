package com.MyFirstApplication.service;

import org.springframework.stereotype.Service;

import com.MyFirstApplication.dto.ForgetPasswordDTO;
import com.MyFirstApplication.dto.UserLoginDTO;
import com.MyFirstApplication.dto.UserRegisterDTO;
import com.MyFirstApplication.model.Response;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public Response UserLogin(UserLoginDTO userLoginDto) {
		// TODO Auto-generated method stub
		return null;
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
