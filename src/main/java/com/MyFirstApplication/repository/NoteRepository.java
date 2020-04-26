package com.MyFirstApplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MyFirstApplication.model.Note;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {

}
