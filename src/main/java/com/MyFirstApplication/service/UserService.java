package com.MyFirstApplication.service;

	import com.MyFirstApplication.dto.ForgetPasswordDTO;
	import com.MyFirstApplication.dto.UserLoginDTO;
	import com.MyFirstApplication.dto.UserRegisterDTO;
	import com.MyFirstApplication.model.Response;


	public interface UserService 
	{	
		Response login(UserLoginDTO userLoginDto);
		Response register(UserRegisterDTO userRegisterDto) throws Exception,NullPointerException;
		Response forget(ForgetPasswordDTO forgetPasswordDto);
		Response reset(long id,String password);
	}


