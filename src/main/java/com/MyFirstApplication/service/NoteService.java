package com.MyFirstApplication.service;
import com.MyFirstApplication.dto.NoteDTO;
import com.MyFirstApplication.model.Response;
public interface NoteService {
Response getAllNotes(String token);
Response createNote(String token,NoteDTO noteDto)throws Exception, NullPointerException;
Response updateNote(String token,int id,NoteDTO noteDto);
Response  deleteNote(String token,int id);
Response sortByTitle(String token);
Response sortByContent(String token);
Response isPined(String token,int id);
Response isTrash(String token,int id);
Response isArchieve(String token,int id);

}
