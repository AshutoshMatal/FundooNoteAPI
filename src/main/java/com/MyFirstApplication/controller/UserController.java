package com.MyFirstApplication.controller;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.MyFirstApplication.dto.UserLoginDTO;
import com.MyFirstApplication.dto.UserRegisterDTO;
import com.MyFirstApplication.model.Response;
import com.MyFirstApplication.model.User;
import com.MyFirstApplication.repository.ConfirmationTokenRepository;
import com.MyFirstApplication.repository.UserRepository;
import com.MyFirstApplication.service.UserServiceImpl;

@RestController
public class UserController{
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	@Autowired
    UserRepository userRepository;
	@Autowired 
	UserServiceImpl userService;
	
	@PostMapping("/register")
	public ResponseEntity<Response> register(@Valid @RequestBody UserRegisterDTO userRegisterDto)throws Exception,NullPointerException{
		Response response=userService.register(userRegisterDto);
		return new ResponseEntity<Response>(response,HttpStatus.OK); 
	}
	@PostMapping("/login")
	public  ResponseEntity<Response> login( @Valid @RequestBody UserLoginDTO userLoginDto)
	{
		Response response=userService.login(userLoginDto);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("/forget")
	public ResponseEntity<Response> forget(@RequestParam String emailId){
		Response response=userService.forget(emailId);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@PutMapping("/reset/{id}")
	public ResponseEntity<Response> reset(@PathVariable long id,@RequestParam String password){
		Response response=userService.reset(id,password);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@PostMapping("/confirm/{token}")
	public String confirmToken( @RequestParam("token")String confirmationToken)
	{
		String response=userService.confirmToken(confirmationToken);
		return response;
	}

}
