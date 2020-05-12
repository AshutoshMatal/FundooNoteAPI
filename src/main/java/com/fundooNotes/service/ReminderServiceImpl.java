package com.fundooNotes.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fundooNotes.dto.ReminderDTO;
import com.fundooNotes.exception.LoginException;
import com.fundooNotes.exception.NoteNotFoundException;
import com.fundooNotes.exception.ReminderNotPresentException;
import com.fundooNotes.message.JwtToken;
import com.fundooNotes.message.MessageInfo;
import com.fundooNotes.model.Note;
import com.fundooNotes.model.Reminder;
import com.fundooNotes.model.Response;
import com.fundooNotes.model.User;
import com.fundooNotes.repository.NoteRepository;
import com.fundooNotes.repository.ReminderRepository;
import com.fundooNotes.repository.UserRepository;
import com.sun.istack.logging.Logger;

@Component
@Service
@PropertySource("classpath:message.properties")
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
	public Response addReminder(String token, int id, ReminderDTO reminderDto) {
		String emailId = jwtOperation.getToken(token);
		User user = userRepository.findByEmailId(emailId);

		if (user == null)
			throw new LoginException(message.User_Not_Exist);

		Note note = noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(400,message.Note_Not_Exist));

		if (!user.getNoteList().contains(note))
			throw new NoteNotFoundException(400,message.Note_Not_Exist_User);

		if (note.getReminder() == null) { 
			Reminder reminder = mapper.map(reminderDto, Reminder.class);
			reminder.setDateAndTime(reminderDto.getDate());
			reminder.setNote(note);
			reminderRepository.save(reminder);
			LOGGER.info("reminder is successfully inserted into table");
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("reminder.add"), message.Reminder_isSet);
		}
		throw new ReminderNotPresentException(400,message.Reminder_isPresent);
	}

	@Override
	public Response showReminder(String token)
	{
		String emailId = jwtOperation.getToken(token);
		User user = userRepository.findByEmailId(emailId);
		if (user == null)
			throw new LoginException(message.User_Not_Exist);
		List<Note> userNoteList = user.getNoteList();
		if (userNoteList == null)
			throw new NoteNotFoundException(400,message.Note_Not_Exist);
		List<Note> notesReminder = new ArrayList<Note>();
		for (Note note : userNoteList) 
		{
			if (note.getReminder() != null)
			{
				notesReminder.add(note);
			}
		}
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("reminder.show"), notesReminder);
	}


	@Override
	public Response updateReminder(String token, int id, ReminderDTO reminderDto) {
		String emailId = jwtOperation.getToken(token);
		User user = userRepository.findByEmailId(emailId);
		if (user == null)
			throw new LoginException(message.User_Not_Exist);
		Note note = noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(400,message.Note_Not_Exist));
		if (!user.getNoteList().contains(note))
			throw new NoteNotFoundException(400,message.Note_Not_Exist_User);
	
		if (note.getReminder() != null) {
			Reminder reminder = note.getReminder();
			reminder.setDateAndTime(reminderDto.getDate());
			reminderRepository.save(reminder);
			LOGGER.info("reminder is updated successfully and save into table");
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("reminder.update"), message.Reminder_isUpdate);
		}
		throw new ReminderNotPresentException(400,message.Reminder_isNotPresent);
	}


	@Override
	public Response deleteReminder(String token, int id) {
		String emailId = jwtOperation.getToken(token);
		User user = userRepository.findByEmailId(emailId);
		if (user == null)
			throw new LoginException(message.User_Not_Exist);
		
		Note note= noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(400,message.Note_Not_Exist));
		
		if (!user.getNoteList().contains(note))
			throw new NoteNotFoundException(400,message.Note_Not_Exist_User);
		
		if (note.getReminder() != null) {
			Reminder reminder = note.getReminder();
			reminderRepository.deleteById(reminder.getId());
			LOGGER.info("records is successfully deleted from table");
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
			environment.getProperty("reminder.delete"), message.Reminder_Delete);
		}

		throw new ReminderNotPresentException(400,message.Reminder_isNotPresent);
	}

	
}
