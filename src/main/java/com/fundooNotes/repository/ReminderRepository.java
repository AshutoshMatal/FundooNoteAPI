package com.fundooNotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fundooNotes.model.Reminder;

public interface ReminderRepository extends JpaRepository<Reminder, Integer>{

}
