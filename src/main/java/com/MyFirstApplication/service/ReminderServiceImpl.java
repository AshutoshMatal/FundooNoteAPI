package com.MyFirstApplication.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.MyFirstApplication.dto.ReminderDTO;
import com.MyFirstApplication.exception.LoginException;
import com.MyFirstApplication.exception.NoteNotFoundException;
import com.MyFirstApplication.exception.ReminderNotPresentException;
import com.MyFirstApplication.message.JwtToken;
import com.MyFirstApplication.message.MessageInfo;
import com.MyFirstApplication.model.Note;
import com.MyFirstApplication.model.Reminder;
import com.MyFirstApplication.model.Response;
import com.MyFirstApplication.model.User;
import com.MyFirstApplication.repository.NoteRepository;
import com.MyFirstApplication.repository.ReminderRepository;
import com.MyFirstApplication.repository.UserRepository;
import com.sun.istack.logging.Logger;

@Component
@Service
@PropertySource("message.properties")
public class ReminderServiceImpl implements ReminderService  {
	@Autowired
	JwtToken jwtOperation;

	@Autowired
	UserRepository userRepository;

	@Autowired
	Environment environment;

	@Autowired
	NoteRepository noteRepository;

	@Autowired
	ModelMapper mapper;

	@Autowired
	MessageInfo message;

	@Autowired
	ReminderRepository reminderRepository;

	private static final Logger LOGGER = Logger.getLogger(NoteServiceImpl.class);


	@Override
	public Response addReminder(String token, int noteId, ReminderDTO reminderDto) {
		String emailId = jwtOperation.getToken(token);
		User user = userRepository.findByEmailId(emailId);

		if (user == null)
			throw new LoginException(400,message.User_Not_Exist);

		Note note = noteRepository.findById(noteId).orElseThrow(() -> new NoteNotFoundException(400,message.Note_Not_Exist));

		if (!user.getNoteList().contains(note))
			throw new NoteNotFoundException(400,message.Note_Not_Exist_User);
	
		if (note.getReminder() == null) { 
			Reminder reminder = mapper.map(reminderDto, Reminder.class);
			reminder.setDateAndTime(reminderDto.getDate());
			reminder.setNote(note);
			reminderRepository.save(reminder);
			LOGGER.info("reminder is successfully inserted into table");
			return new Response(environment.getProperty("reminder.add"),
					Integer.parseInt(environment.getProperty("status.success.code")), message.Reminder_isSet);
		}
		throw new ReminderNotPresentException(400,message.Reminder_isPresent);
	}

	@Override
	public Response showReminder(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateReminder(String token, int id, ReminderDTO reminderDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteReminder(String token, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
