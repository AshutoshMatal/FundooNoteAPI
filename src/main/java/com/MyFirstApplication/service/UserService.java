package com.MyFirstApplication.service;
import com.MyFirstApplication.dto.ForgetPasswordDTO;
import com.MyFirstApplication.dto.ResetPasswordDTO;
import com.MyFirstApplication.dto.UserLoginDTO;
import com.MyFirstApplication.dto.UserRegisterDTO;
import com.MyFirstApplication.model.Note;
import com.MyFirstApplication.model.Response;


public interface UserService 
{	
	Response login(UserLoginDTO userLoginDto);
	Response register(UserRegisterDTO userRegistrationDto)throws Exception,NullPointerException;
	Response forget(ForgetPasswordDTO forgetPasswordDto);
	Response reset(String token,ResetPasswordDTO resetPasswordDto);
	Response validateUser(String token);
	
}


