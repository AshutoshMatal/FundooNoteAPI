package com.fundooNotes.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fundooNotes.dto.NoteDTO;
import com.fundooNotes.exception.LoginException;
import com.fundooNotes.exception.NoteNotExistInTrash;
import com.fundooNotes.exception.NoteNotFoundException;
import com.fundooNotes.message.JwtToken;
import com.fundooNotes.message.MessageInfo;
import com.fundooNotes.model.Note;
import com.fundooNotes.model.Response;
import com.fundooNotes.model.User;
import com.fundooNotes.repository.LabelRepository;
import com.fundooNotes.repository.NoteRepository;
import com.fundooNotes.repository.UserRepository;
import com.sun.istack.logging.Logger;
@Component
@Service
@PropertySource("classpath:message.properties")
public class NoteServiceImpl implements NoteService{
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
	User user;

	@Autowired
	LabelRepository labelRepository;

	private static final Logger LOGGER = Logger.getLogger(NoteServiceImpl.class);


	@Override
	public Response getAllNotes(String token) 
	{
		String emailId = jwtOperation.getToken(token);
		User user = userRepository.findByEmailId(emailId);
		if (user == null)
			throw new LoginException(message.User_Not_Exist);
		if (user.getNoteList() == null)
			throw new NoteNotFoundException(400,message.Note_Not_Exist);
		List<Note> allNotes = user.getNoteList().stream().filter(note -> !note.isTrash())
				.collect(Collectors.toList());
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("note.getallnotes"), allNotes);
	}

	@Override
	public Response createNote(String token,NoteDTO noteDto)throws Exception, NullPointerException
	{
		String emailid = jwtOperation.getToken(token);
		User user = userRepository.findByEmailId(emailid);
		if (user == null)
			throw new LoginException(message.User_Not_Exist);
		Note note = mapper.map(noteDto, Note.class);
		note.setUser(user);
		noteRepository.save(note);
		LOGGER.info("Note successfully created into Note table");
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("note.create"), message.Note_Create);
	}
	@Override
	public Response updateNote(String token,int id,NoteDTO noteDto) 
	{
		String emailId = jwtOperation.getToken(token);
		User user = userRepository.findByEmailId(emailId);
		if (user == null)
			throw new LoginException(message.User_Not_Exist);
		Note note = noteRepository.findById(id)
				.orElseThrow(() -> new NoteNotFoundException(400,message.Note_Not_Exist));
		if (note.getUser().getId() != user.getId())
			throw new NoteNotFoundException(400,message.Note_Not_Exist_User);
		note.setContent(noteDto.getContent());
		note.setTitle(noteDto.getTitle());
		noteRepository.save(note);
		LOGGER.info("Note is successfully update and saved in tabel");
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("note.update"), message.Note_Update);
	}
	@Override
	public Response deleteNote(String token,int id) {
		String emailId = jwtOperation.getToken(token);
		User user = userRepository.findByEmailId(emailId);
		if (user == null)
			throw new LoginException(message.User_Not_Exist);	
		Note note = noteRepository.findById(id)
				.orElseThrow(() -> new NoteNotFoundException(400,message.Note_Not_Exist));
		if (note.getUser().getId() != user.getId())
			throw new NoteNotFoundException(400,message.Note_Not_Exist_User);
		if (note.isTrash() == true) {
			noteRepository.deleteById(id);
			LOGGER.info("Note is successfully deleted from Note table");
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("note.delete"), message.Note_Delete);
		}
		throw new NoteNotExistInTrash(400,message.Note_Not_Exist_In_Trash);
	}
	@Override
	public Response sortByTitle(String token) 
	{
		String emailId = jwtOperation.getToken(token);
		User user = userRepository.findByEmailId(emailId);
		if (user == null)
			throw new LoginException(message.User_Not_Exist);	
		if (user.getNoteList() == null)
			throw new NoteNotFoundException(400,message.Note_Not_Exist);	
		List<Note> sortedList = user.getNoteList().stream()
				.sorted((list1, list2) -> list1.getTitle().compareTo(list2.getTitle())).collect(Collectors.toList());
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("note.getallnotes"), sortedList);
	}
	@Override
	public Response sortByContent(String token)
	{
		String emailId = jwtOperation.getToken(token);
		User user = userRepository.findByEmailId(emailId);
		if (user == null)
			throw new LoginException(message.User_Not_Exist);	
		if (user.getNoteList() == null)
			throw new NoteNotFoundException(400,message.Note_Not_Exist);
		List<Note> sortedList = user.getNoteList().stream()
				.sorted((list1, list2) -> list1.getContent().compareTo(list2.getContent()))
				.collect(Collectors.toList());
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("note.getallnotes"), sortedList);
	}
	@Override
	public Response isPined(String token,int id)
	{
		String emailId = jwtOperation.getToken(token);
		User user = userRepository.findByEmailId(emailId);
		if (user == null)
			throw new LoginException(message.User_Not_Exist);
		Note note = noteRepository.findById(id)
				.orElseThrow(() -> new NoteNotFoundException(400,message.Note_Not_Exist));
		if (note.getUser().getId() != user.getId())
			throw new NoteNotFoundException(400,message.Note_Not_Exist);
		if (note.isPined() == false)
		{
			note.setPined(true);
			noteRepository.save(note);
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("note.ispin"), message.Note_Pin);
		} else
		{
			note.setPined(false);
			noteRepository.save(note);
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("note.isunpin"), message.Note_UnPin);
		}
	}
	@Override
	public Response isTrash(String token,int id)
	{
		String emailId = jwtOperation.getToken(token);
		User user = userRepository.findByEmailId(emailId);
		if (user == null)
			throw new LoginException(message.User_Not_Exist);
		Note note = noteRepository.findById(id)
				.orElseThrow(() -> new NoteNotFoundException(400,message.Note_Not_Exist));
		if (note.getUser().getId() != user.getId())
			throw new NoteNotFoundException(400,message.Note_Not_Exist_User);
		if (note.isTrash() == false) 
		{
			note.setTrash(true);
			noteRepository.save(note);
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("note.istrash"), message.Note_Trash);
		}
		else 
		{
			note.setTrash(false);
			noteRepository.save(note);
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("note.isuntrash"), message.Note_UnTrash);
		}
	}

	@Override
	public Response isArchieve(String token,int id) 
	{
		String emailId = jwtOperation.getToken(token);
		User user = userRepository.findByEmailId(emailId);
		if (user == null)
			throw new LoginException(message.User_Not_Exist);
		Note note = noteRepository.findById(id)
				.orElseThrow(() -> new NoteNotFoundException(400,message.Note_Not_Exist));
		if (note.getUser().getId() != user.getId())
			throw new NoteNotFoundException(400,message.Note_Not_Exist_User);
		if (note.isArchieve() == false) 
		{
			note.setArchieve(true);
			noteRepository.save(note);
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("note.isarchive"), message.Note_Archive);
		} 
		else 
		{
			note.setArchieve(false);
			noteRepository.save(note);
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("note.isunarchive"), message.Note_UnArchive);
		}


	}
}