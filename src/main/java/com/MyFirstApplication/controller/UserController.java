package com.MyFirstApplication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MyFirstApplication.dto.ForgetPasswordDTO;
import com.MyFirstApplication.dto.ResetPasswordDTO;
import com.MyFirstApplication.dto.UserLoginDTO;
import com.MyFirstApplication.dto.UserRegisterDTO;
import com.MyFirstApplication.model.Note;
import com.MyFirstApplication.model.Response;
import com.MyFirstApplication.model.User;
import com.MyFirstApplication.repository.ConfirmationTokenRepository;
//import com.MyFirstApplication.repository.ConfirmationTokenRepository;
import com.MyFirstApplication.repository.UserRepository;
import com.MyFirstApplication.service.UserServiceImpl;

@RestController
public class UserController{
	@Autowired 
	UserServiceImpl userService;
	
	@PostMapping("/register")
	public ResponseEntity<Response> register(@Valid @RequestBody UserRegisterDTO userRegisterDto)throws Exception,NullPointerException{
		Response response=userService.register(userRegisterDto);
		return new ResponseEntity<Response>(response,HttpStatus.OK); 
	}
	@PostMapping("/login")
	public  ResponseEntity<Response> login( @RequestBody UserLoginDTO userLoginDto)
	{
		Response response=userService.login(userLoginDto);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("/forget")
	public ResponseEntity<Response> forget( @RequestBody ForgetPasswordDTO forgetPasswordDto){
		Response response=userService.forget(forgetPasswordDto);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@PutMapping("/reset")
	public ResponseEntity<Response> reset(@RequestBody ResetPasswordDTO resetPasswordDto,@PathVariable String token){
		Response response=userService.reset(token,resetPasswordDto);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	 @RequestMapping(value="/validate/{token}", method= {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<Response> validateUser( @RequestParam("token") String token)
	{
		Response response=userService.validateUser(token);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	 
	 

}
