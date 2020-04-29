package com.MyFirstApplication.service;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import com.MyFirstApplication.dto.ForgetPasswordDTO;
import com.MyFirstApplication.dto.ResetPasswordDTO;
import com.MyFirstApplication.dto.UserLoginDTO;
import com.MyFirstApplication.dto.UserRegisterDTO;
import com.MyFirstApplication.exception.ForgetPasswordException;
import com.MyFirstApplication.exception.LoginException;
import com.MyFirstApplication.exception.RegistrationException;
import com.MyFirstApplication.exception.ResetPasswordException;
import com.MyFirstApplication.exception.ValidateException;
import com.MyFirstApplication.message.MessageInfo;
import com.MyFirstApplication.message.MessageResponse;
import com.MyFirstApplication.model.Response;
import com.MyFirstApplication.model.User;
import com.MyFirstApplication.repository.UserRepository;
import com.MyFirstApplication.utility.JwtToken;
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

	@Transactional
	@Override
	public Response login(UserLoginDTO userLoginDto) 
	{
		User user = userRepository.findByEmailId(userLoginDto.getEmailId());
		String token = jwtObject.generateToken(userLoginDto.getEmailId());
		System.out.println(token);
		// USER PRESENT OR NOT
		if (user == null)
			throw new LoginException(400,"Error");
		// CHECK VALIDATION
		if (user.getIsValidate()) {
			if ((user.getPassword()).equals(userLoginDto.getPassword())) {
				LOGGER.info("login successfully done");
				return new Response(environment.getProperty("status.login.success"),
						Integer.parseInt(environment.getProperty("status.success.code")), token);
					} else {
						return new Response(environment.getProperty("status.password.incorrect"),
						Integer.parseInt(environment.getProperty("status.redirect.code")), message.Invalide_Password);
					}
				} else {
					return new Response(environment.getProperty("status.email.notverify"),
					Integer.parseInt(environment.getProperty("status.bad.code")), message.User_Not_Verify);
				}
			}
	

	@Transactional
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
		return new Response(environment.getProperty("status.user.register"),
				Integer.parseInt(environment.getProperty("status.success.code")), message.Registration_Done);
	}

	@Transactional
	@Override
	public Response forget(ForgetPasswordDTO forgetPasswordDto)
	{

		User user = userRepository.findByEmailId(forgetPasswordDto.getEmailId());
		// CHECK USER PRESENT OR NOT
		if (user == null)
			throw new ForgetPasswordException(400,"Error");
		// VALIDATION 
		if (user.getIsValidate())
		{
			String token = jwtObject.generateToken(forgetPasswordDto.getEmailId());
			User userData = userRepository.findByEmailId(forgetPasswordDto.getEmailId());
			System.out.println(token);
			emailId = messageResponse.passwordReset(forgetPasswordDto.getEmailId(), user.getUsername(), token);
			emailService.sendEmail(emailId);
			return new Response(environment.getProperty("status.token.send"),
					Integer.parseInt(environment.getProperty("status.success.code")), message.Token_Send);
		} 
		else
		{
			return new Response(environment.getProperty("status.email.notverify"),
					Integer.parseInt(environment.getProperty("status.bad.code")),message.User_Not_Verify);
		}
	}


	@Override
	public Response reset(String token,ResetPasswordDTO resetPasswordDto) 
	{
		String checkEmail = jwtObject.getToken(token);
		User userUpdate = userRepository.findByEmailId(checkEmail);
		// USER PRESENT OR NOT
		if (userUpdate == null)
			throw new ResetPasswordException(400,"");
		// CHECK PASSWORD AND CONFIRM ARE EQUALS OR NOT
		if (resetPasswordDto.getConfirmPassword().equals(resetPasswordDto.getPassword())) {
			userUpdate.setPassword(resetPasswordDto.getPassword());
			userRepository.save(userUpdate);
			LOGGER.info("Password is successfully update and saved in table");
			return new Response(environment.getProperty("status.password.update"),
			Integer.parseInt(environment.getProperty("status.success.code")), message.Update_Password);
		} else {
			return new Response(environment.getProperty("status.password.incorrect"),
			Integer.parseInt(environment.getProperty("status.redirect.code")),message.Bad_Request);
		}
	}

	@Override
	public Response validateUser(String token) 
	{
		String emailId = jwtObject.getToken(token);
		User userIsVerified = userRepository.findByEmailId(emailId);
		// USER PRESENT OR NOT
		if (userIsVerified == null)
			throw new ValidateException(400,"Error");
		userIsVerified.setIsValidate(true);
		userRepository.save(userIsVerified);
		LOGGER.info("IsValidate is successfully set to true");
		return new Response(environment.getProperty("status.email.isverify"),
				Integer.parseInt(environment.getProperty("status.success.code")), message.Verify_User);
	}

}