package com.MyFirstApplication.service;
import java.util.Optional;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import com.MyFirstApplication.dto.UserLoginDTO;
import com.MyFirstApplication.dto.UserRegisterDTO;
import com.MyFirstApplication.model.ConfirmationToken;
import com.MyFirstApplication.model.Response;
import com.MyFirstApplication.model.User;
import com.MyFirstApplication.repository.ConfirmationTokenRepository;
import com.MyFirstApplication.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	User user=new User();
	ModelMapper modelMapper=new ModelMapper();


	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	UserRepository userRepository;
	@Transactional
	@Override
	public Response login(UserLoginDTO userLoginDto) 
	{
		modelMapper.map(userLoginDto,user);
		Optional<User> user=userRepository.findByUsername(userLoginDto.getUsername());
		//if(user.get().getUsername().equals(userLoginDto.getUsername())&&user.get().getPassword().equals(user))
		//{
		return new Response("Login successfully",200);
		//}
		//return new Response("Login failed",400);
	}
	@Transactional
	@Override
	public Response register(UserRegisterDTO userRegisterDto)throws Exception,NullPointerException
	{
		Optional<User> optional = userRepository.findByEmailId(user.getEmailId());
		modelMapper.map( userRegisterDto,user);

		if (userRepository.findByUsername(user.getUsername())
				.equals(userRepository.findByPassword(user.getPassword())))
		{
			userRepository.save(user);
			ConfirmationToken confirmationToken = new ConfirmationToken(user);

			confirmationTokenRepository.save(confirmationToken);
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(user.getEmailId());
			mailMessage.setSubject("Complete Registration!");
			mailMessage.setFrom("ashutoshmatal33@gmail.com");
			mailMessage.setText("To confirm your account, please click here : "
					+"http://localhost:8080/confirm?token="+confirmationToken.getConfirmationToken());

			emailService.sendEmail(mailMessage);

			//HttpHeaders response = new HttpHeaders();
			//response.add("emailId", user.getEmailId());

			return new Response("Registration successfully",200);

		}
		return new Response("Registration faied",400);

	}

	@Transactional
	@Override
	public Response forget(String emailId)
	{

		modelMapper.map(emailId,user);
		Optional<User> user = userRepository.findByEmailId(emailId);
		if(user==null)
		{
			return new Response("false", 400);
		}
		return new Response("true", 200);
	}

	@Transactional
	@Override
	public Response reset(long id, String password) 
	{
		Optional<User> optionalUser=userRepository.findById(id);
		if(optionalUser.isPresent())
		{
			User user=optionalUser.get();
			user.setPassword(password);
			userRepository.save(user);
			return new Response("Successfully done",200);
		}
		return new Response("Failed",400);
	}

	@Override
	public Response confirmToken(String confirmationToken) 
	{
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		Optional<User> optional = userRepository.findByEmailId(token.getUser().getEmailId());

		if (token!=null) 
		{
			User user=optional.get();
			userRepository.save(user);

			return new Response("Successfully done",200);

		}
		return new Response("Error",400);
	}
}