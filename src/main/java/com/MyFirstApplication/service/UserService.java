package com.MyFirstApplication.service;
import com.MyFirstApplication.dto.UserLoginDTO;
import com.MyFirstApplication.dto.UserRegisterDTO;
import com.MyFirstApplication.model.Response;


	public interface UserService 
	{	
		Response login(UserLoginDTO userLoginDto);
		Response register(UserRegisterDTO userRegistrationDto)throws Exception,NullPointerException;
		Response forget(String emailId);
		Response reset(long id,String password);
		String confirmToken(String confirmationToken);
	}


