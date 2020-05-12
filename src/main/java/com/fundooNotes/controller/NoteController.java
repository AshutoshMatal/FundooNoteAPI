package com.fundooNotes.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fundooNotes.dto.NoteDTO;
import com.fundooNotes.dto.ReminderDTO;
import com.fundooNotes.model.Response;
import com.fundooNotes.repository.NoteRepository;
import com.fundooNotes.service.NoteServiceImpl;
import com.fundooNotes.service.ReminderServiceImpl;
@RequestMapping("/notes")
@RestController
@CrossOrigin(origins="*")
public class NoteController {
	@Autowired
	NoteRepository noteRepository;
	@Autowired
	ReminderServiceImpl reminderService;

	@Autowired
	NoteServiceImpl noteService;
	@GetMapping("/findAll")
	public ResponseEntity<Response> getAllNotes(@RequestHeader String token)
	{
		Response response=noteService.getAllNotes(token);
		return new ResponseEntity<Response>(response,HttpStatus.OK); 
	}
	@PostMapping("/create")
	public ResponseEntity<Response> createNote(@RequestHeader String token, @RequestBody NoteDTO noteDto)throws Exception, NullPointerException{
		Response response=noteService.createNote(token,noteDto);
		return new ResponseEntity<Response>(response,HttpStatus.OK); 
	}
	@PostMapping("/update/{id}")
	public ResponseEntity<Response> updateNote(@RequestHeader String token, @PathVariable int id,@RequestBody NoteDTO noteDto){
		Response response=noteService.updateNote(token,id,noteDto);
		return new ResponseEntity<Response>(response,HttpStatus.OK); 
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteNote(@RequestHeader String token, @PathVariable int id){
		Response response=noteService.deleteNote(token,id);
		return new ResponseEntity<Response>(response,HttpStatus.OK); 
	}
	@GetMapping("/sortByTitle")
	public ResponseEntity<Response> sortByTitle(@RequestHeader String token){
		Response response=noteService.sortByTitle(token);
		return new ResponseEntity<Response>(response,HttpStatus.OK); 
	}
	@GetMapping("/sortByContent")
	public ResponseEntity<Response> sortByContent(@RequestHeader String token){
		Response response=noteService.sortByContent(token);
		return new ResponseEntity<Response>(response,HttpStatus.OK); 
	}
	@PutMapping("/isTrash/{id}")
	public ResponseEntity<Response> isTrash(@RequestHeader String token, @PathVariable int id){
		Response response=noteService.isTrash(token,id);
		return new ResponseEntity<Response>(response,HttpStatus.OK); 
	}
	@PutMapping("/isPin/{id}")
	public ResponseEntity<Response> isPined(@RequestHeader String token, @PathVariable int id){
		Response response=noteService.isPined(token,id);
		return new ResponseEntity<Response>(response,HttpStatus.OK); 
	}
	@PutMapping("/isArchive/{id}")
	public ResponseEntity<Response> isArchieve(@RequestHeader String token, @PathVariable int id){
		Response response=noteService.isArchieve(token,id);
		return new ResponseEntity<Response>(response,HttpStatus.OK); 
	}
	@PostMapping("/addReminder")
	public ResponseEntity<Response> addReminder(@RequestHeader String token,@Valid @RequestBody ReminderDTO reminderDto, @PathVariable int id){
		Response response=reminderService.addReminder(token,id,reminderDto);
		return new ResponseEntity<Response>(response,HttpStatus.OK); 
	}
	@PutMapping("updateReminder/{noteId}")
	public ResponseEntity<Response> updateReminder(@RequestHeader String token, @PathVariable int id,@RequestBody ReminderDTO reminderDto) {
		Response response = reminderService.updateReminder(token, id, reminderDto);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@DeleteMapping("/deleteReminder/{noteId}")
	public ResponseEntity<Response> deleteReminder(@RequestHeader String token, @PathVariable int id) {
		Response response = reminderService.deleteReminder(token, id);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}



}
