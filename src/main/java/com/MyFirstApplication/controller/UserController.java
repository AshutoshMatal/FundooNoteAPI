package com.MyFirstApplication.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MyFirstApplication.dto.ForgetPasswordDTO;
import com.MyFirstApplication.dto.UserLoginDTO;
import com.MyFirstApplication.dto.UserRegisterDTO;
import com.MyFirstApplication.model.Response;
import com.MyFirstApplication.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired 
	UserService userService;

	@PostMapping("/login")
	public  ResponseEntity<Response> login(@RequestBody UserLoginDTO userLoginDto)
	{
		Response response=userService.UserLogin(userLoginDto);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@PostMapping("/register")
	public ResponseEntity<Response> register(@RequestBody UserRegisterDTO userRegisterDto)throws Exception {
		Response response=userService.UserRegister(userRegisterDto);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@PostMapping("/forget")
	public ResponseEntity<Response> forget(@RequestBody ForgetPasswordDTO forgetPasswordDto){
		Response response=userService.forgetPassword(forgetPasswordDto);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@PutMapping("/reset/{id}")
	public ResponseEntity<Response> reset(@PathVariable int id,@RequestParam String password){
		Response response=userService.resetPassword(id,password);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

}
