package com.MyFirstApplication.controller;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.MyFirstApplication.dto.NoteDTO;
import com.MyFirstApplication.model.Response;
import com.MyFirstApplication.repository.NoteRepository;
import com.MyFirstApplication.service.NoteServiceImpl;

@RestController
public class NoteController {
	@Autowired
	NoteRepository noteRepository;

	@Autowired
	NoteServiceImpl noteService;
	@PostMapping("/findAll")
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
	@RequestMapping(path = "/delete/{id}")
	public ResponseEntity<Response> deleteNote(@RequestHeader String token, @PathVariable int id){
		Response response=noteService.deleteNote(token,id);
		return new ResponseEntity<Response>(response,HttpStatus.OK); 
	}
	
	public ResponseEntity<Response> sortByTitle(@RequestHeader String token){
		Response response=noteService.sortByTitle(token);
		return new ResponseEntity<Response>(response,HttpStatus.OK); 
	}


	public ResponseEntity<Response> sortByContent(@RequestHeader String token){
		Response response=noteService.sortByContent(token);
		return new ResponseEntity<Response>(response,HttpStatus.OK); 
	}


	public ResponseEntity<Response> isTrash(@RequestHeader String token, @PathVariable int id){
		Response response=noteService.isTrash(token,id);
		return new ResponseEntity<Response>(response,HttpStatus.OK); 
	}
	public ResponseEntity<Response> isPined(@RequestHeader String token, @PathVariable int id){
		Response response=noteService.isPined(token,id);
		return new ResponseEntity<Response>(response,HttpStatus.OK); 
	}

	public ResponseEntity<Response> isArchieve(@RequestHeader String token, @PathVariable int id){
		Response response=noteService.isArchieve(token,id);
		return new ResponseEntity<Response>(response,HttpStatus.OK); 
	}

}
