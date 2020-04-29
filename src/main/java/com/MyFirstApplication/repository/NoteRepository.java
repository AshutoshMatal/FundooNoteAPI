package com.MyFirstApplication.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MyFirstApplication.model.Note;
import com.MyFirstApplication.model.User;


@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
	Note findByTitle(String title);
	
	Note findByContent(String content);
	
	Note findByUser(User user);
	
}
