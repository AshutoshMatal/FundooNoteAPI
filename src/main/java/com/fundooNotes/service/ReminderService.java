package com.fundooNotes.service;

import com.fundooNotes.dto.ReminderDTO;
import com.fundooNotes.model.Response;

public interface ReminderService {
	Response addReminder(String token, int id, ReminderDTO reminderDto);
	Response showReminder(String token);
	Response updateReminder(String token, int id, ReminderDTO reminderDto);
	Response deleteReminder(String token, int id);
	

}
