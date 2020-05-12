package com.fundooNotes.service;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.fundooNotes.dto.LabelDTO;
import com.fundooNotes.exception.LabelNotFound;
import com.fundooNotes.exception.LoginException;
import com.fundooNotes.message.JwtToken;
import com.fundooNotes.message.MessageInfo;
import com.fundooNotes.model.Label;
import com.fundooNotes.model.Response;
import com.fundooNotes.model.User;
import com.fundooNotes.repository.LabelRepository;
import com.fundooNotes.repository.NoteRepository;
import com.fundooNotes.repository.UserRepository;
import com.sun.istack.logging.Logger;

@Component
@Service
@PropertySource("classpath:message.properties")
public class LabelServiceImpl implements LabelService {
	@Autowired
	JwtToken jwtToken;

	@Autowired
	UserRepository userRepository;

	@Autowired
	Environment environment;

	@Autowired
	ModelMapper mapper;

	@Autowired
	LabelRepository labelRepository;

	@Autowired
	NoteRepository noteRepository;

	@Autowired
	MessageInfo message;
	
	private static final Logger LOGGER = Logger.getLogger(NoteServiceImpl.class);

	 @Override
		public Response showLabel(String token) {
		 String emailId = jwtToken.getToken(token);
			User user = userRepository.findByEmailId(emailId);
			// check if user is present or not
			if (user == null)
				throw new LoginException(message.User_Not_Exist);
			// check if label is present or not
			if(user.getLabelList() == null)
				throw new LabelNotFound(400,message.Label_Not_Exist);
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
			environment.getProperty("label.getAllLabels"), user.getLabelList());
		}
	@Override
	public Response addLabel(String token,LabelDTO labelDto) 
	{
		String emailId = jwtToken.getToken(token);
		User user = userRepository.findByEmailId(emailId);
		// check if user is present or not
		if (user == null)
			throw new LoginException(message.User_Not_Exist);
		Label label = mapper.map(labelDto, Label.class);
		label.setUser(user);
		labelRepository.save(label);
		LOGGER.info("User is successfully inserted into table");
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
		environment.getProperty("label.create"), message.Label_Create);
	}	@Transactional
	@Override
	public Response editLabel(String token,LabelDTO labelDto, int id) {
		String email = jwtToken.getToken(token);
		User user = userRepository.findByEmailId(email);
		// check if user is present or not
		if (user == null)
			throw new LoginException(message.User_Not_Exist);
		// check if label is present or not
		Label label = labelRepository.findById(id).orElseThrow(() ->new LabelNotFound(400,message.Label_Not_Exist));
		label.setLabelName(labelDto.getLabelName());
		labelRepository.save(label);
		LOGGER.info("label is successfully updated and save into table");
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
		environment.getProperty("label.update"), message.Label_Update);
	}

	@Override
	public Response deleteLabel(String token,int id) {
		String email = jwtToken.getToken(token);
		User user = userRepository.findByEmailId(email);
		// check if user is present or not
		if (user == null)
			throw new LoginException(message.User_Not_Exist);
		// check if label is present or not
		if (labelRepository.findById(id) == null)
			throw new LabelNotFound(400,message.Label_Not_Exist);
		labelRepository.deleteById(id);
		LOGGER.info("label is successfully deleted from table");
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
		environment.getProperty("label.delete"), message.Label_Delete);
	}
	
	
}


