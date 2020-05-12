package com.fundooNotes.service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.fundooNotes.dto.ForgetPasswordDTO;
import com.fundooNotes.dto.ResetPasswordDTO;
import com.fundooNotes.dto.UserLoginDTO;
import com.fundooNotes.dto.UserRegisterDTO;
import com.fundooNotes.exception.ForgetPasswordException;
import com.fundooNotes.exception.LoginException;
import com.fundooNotes.exception.RegistrationException;
import com.fundooNotes.exception.ResetPasswordException;
import com.fundooNotes.exception.ValidateException;
import com.fundooNotes.message.JwtToken;
import com.fundooNotes.message.MessageInfo;
import com.fundooNotes.message.MessageResponse;
import com.fundooNotes.model.Response;
import com.fundooNotes.model.User;
import com.fundooNotes.repository.UserRepository;
import com.sun.istack.logging.Logger;


@Service
@PropertySource("classpath:message.properties")
public class UserServiceImpl implements UserService {

	@Autowired
	private EmailServiceImpl emailService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Environment environment;
	@Autowired
	private MessageInfo message;

	@Autowired
	private JwtToken jwtObject;

	@Autowired
	private MessageResponse messageResponse;

	SimpleMailMessage emailId;

	@Autowired
	private ModelMapper mapper;

	private static final Logger LOGGER = Logger.getLogger(NoteServiceImpl.class);


	@Override
	public Response login(UserLoginDTO userLoginDto) 
	{
		User user = userRepository.findByEmailId(userLoginDto.getEmailId());
		String token = jwtObject.generateToken(userLoginDto.getEmailId());

		// USER PRESENT OR NOT
		if (user == null)
			throw new LoginException(message.User_Not_Exist);
		// CHECK VALIDATION
		if (user.getIsValidate()) 
		{
			if ((user.getPassword()).equals(userLoginDto.getPassword())) 
			{
				LOGGER.info("login successfully done");
				return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
						environment.getProperty("status.login.success"),token);
			} 
			else 
			{
				return new Response(Integer.parseInt(environment.getProperty("status.redirect.code")),
						environment.getProperty("status.password.incorrect"), message.Invalide_Password);
			}
		}
		else
		{
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notverify"), message.User_Not_Verify);
		}
	}

	@Override
	public Response register(UserRegisterDTO userRegisterDto)throws Exception,NullPointerException
	{
		String checkEmail = userRegisterDto.getEmailId();
		User userIsPresent = userRepository.findByEmailId(checkEmail);
		// CHECK USER PRESENT OR NOT
		if (userIsPresent != null) 
			throw new RegistrationException(400,message.User_Exist);
		User user = mapper.map(userRegisterDto, User.class);
		String token = jwtObject.generateToken(user.getEmailId());
		System.out.println(token);
		emailId= messageResponse.verifyMail(user.getEmailId(), user.getUsername(), token);
		emailService.sendEmail(emailId);
		userRepository.save(user);
		LOGGER.info("User Data is Successfully saved in table");
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("status.user.register"), message.Registration_Done);
	}

	@Override
	public Response forget(ForgetPasswordDTO forgetPasswordDto)
	{
		User userData = userRepository.findByEmailId(forgetPasswordDto.getEmailId());
		// CHECK USER PRESENT OR NOT
		if (userData == null)
			throw new ForgetPasswordException(400,message.User_Exist);
		// VALIDATION 
		if (userData.getIsValidate())
		{
			String token = jwtObject.generateToken(forgetPasswordDto.getEmailId());
			User user = userRepository.findByEmailId(forgetPasswordDto.getEmailId());
			emailId = messageResponse.passwordReset(forgetPasswordDto.getEmailId(), user.getUsername(), token);
			emailService.sendEmail(emailId);
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("status.token.send"), message.Token_Send);
		} 
		else
		{
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notverify"),message.User_Not_Verify);
		}
	}


	@Override
	public Response reset(String token, ResetPasswordDTO resetPasswordDto) 
	{
		String checkEmail = jwtObject.getToken(token);
		User user = userRepository.findByEmailId(checkEmail);
		// USER PRESENT OR NOT
		if (user == null)
			throw new ResetPasswordException(400,message.User_Exist);
		// CHECK PASSWORD AND CONFIRM ARE EQUALS OR NOT
		if (resetPasswordDto.getConfirmPassword().equals(resetPasswordDto.getPassword())) {
			user.setPassword(resetPasswordDto.getPassword());
			userRepository.save(user);
			LOGGER.info("Password is successfully update and saved in table");
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("status.password.update"), message.Update_Password);
		}
		else 
		{
			return new Response(Integer.parseInt(environment.getProperty("status.redirect.code")),
					environment.getProperty("status.password.incorrect"), message.Bad_Request);
		}
	}

	@Override
	public Response validateUser(String token) 
	{
		String emailId = jwtObject.getToken(token);
		User userIsVerified = userRepository.findByEmailId(emailId);
		// USER PRESENT OR NOT
		if (userIsVerified == null)
			throw new ValidateException(400,message.User_Not_Exist);
		userIsVerified.setIsValidate(true);
		userRepository.save(userIsVerified);
		LOGGER.info("IsValidate is successfully set to true");
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("status.email.isverify"), message.Verify_User);
	}

}