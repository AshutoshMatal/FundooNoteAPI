package com.MyFirstApplication.service;



import com.MyFirstApplication.dto.NoteDTO;
import com.MyFirstApplication.model.Response;



public interface NoteService {
Response getAllNotes();
Response createNote(NoteDTO noteDto)throws Exception, NullPointerException;
Response updateNote(long id,NoteDTO noteDto);
Response  deleteNote(long id);

}
