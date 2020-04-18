package com.MyFirstApplication.service;

import com.MyFirstApplication.dto.ForgetPasswordDTO;
import com.MyFirstApplication.dto.UserLoginDTO;
import com.MyFirstApplication.dto.UserRegisterDTO;
import com.MyFirstApplication.model.Response;


public interface UserService 
{	
	Response UserLogin(UserLoginDTO userLoginDto);
	Response UserRegister(UserRegisterDTO userRegisterDto) throws Exception,NullPointerException;
	Response forgetPassword(ForgetPasswordDTO forgetPasswordDto);
	Response resetPassword(int id,String username);
}
