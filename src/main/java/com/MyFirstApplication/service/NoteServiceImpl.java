package com.MyFirstApplication.service;


import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MyFirstApplication.dto.NoteDTO;
import com.MyFirstApplication.exception.ErrorException;
import com.MyFirstApplication.model.Note;
import com.MyFirstApplication.model.Response;
import com.MyFirstApplication.repository.NoteRepository;

@Service
public class NoteServiceImpl implements NoteService{
	Note note=new Note();
	ModelMapper modelMapper=new ModelMapper();
	@Autowired
	NoteRepository noteRepository;

	@Override
	public Response getAllNotes() {
		
		noteRepository.findAll();
		
		return new Response("Added note successfully",200);
	
	}
	@Transactional
	@Override
	public Response createNote(NoteDTO noteDto)throws Exception, NullPointerException {
		//modelMapper.map(noteDto,note);
		noteRepository.save(note);
		return new Response("Added note successfully",200);
	}
	@Transactional
	@Override
	public Response updateNote(long id,NoteDTO noteDto) {
		modelMapper.map(noteDto,note);
		noteRepository.findById(id);
		noteDto.setTitle(noteDto.getTitle());
		noteDto.setContent(noteDto.getContent());
		noteRepository.save(note);
        return new Response("Update successfully",200);
	}

	@Override
	public Response deleteNote(long id) {
		Optional<Note> optional=noteRepository.findById(id);
		if(optional.isPresent())
		{
			noteRepository.deleteById(id);
			return new Response("Deleted successfully",200);
		}
		throw new ErrorException(400, "Error");
	}
	
}
