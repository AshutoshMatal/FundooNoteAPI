package com.MyFirstApplication.service;

import com.MyFirstApplication.dto.ReminderDTO;
import com.MyFirstApplication.model.Response;

public interface ReminderService {
	Response addReminder(String token, int id, ReminderDTO reminderDto);
	Response showReminder(String token);
	Response updateReminder(String token, int id, ReminderDTO reminderDto);
	Response deleteReminder(String token, int id);
	

}
