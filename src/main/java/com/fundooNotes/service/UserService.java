package com.fundooNotes.service;
import com.fundooNotes.dto.ForgetPasswordDTO;
import com.fundooNotes.dto.ResetPasswordDTO;
import com.fundooNotes.dto.UserLoginDTO;
import com.fundooNotes.dto.UserRegisterDTO;
import com.fundooNotes.model.Note;
import com.fundooNotes.model.Response;


public interface UserService 
{	
	Response login(UserLoginDTO userLoginDto);
	Response register(UserRegisterDTO userRegistrationDto)throws Exception,NullPointerException;
	Response forget(ForgetPasswordDTO forgetPasswordDto);
	Response reset(String token,ResetPasswordDTO resetPasswordDto);
	Response validateUser(String token);
	
}


