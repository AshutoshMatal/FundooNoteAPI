package com.fundooNotes.service;

import com.fundooNotes.dto.LabelDTO;
import com.fundooNotes.model.Response;

public interface LabelService {
	Response addLabel(String token,LabelDTO labelDto);
	Response editLabel(String token,LabelDTO labelDto,int id);
	Response deleteLabel(String token,int id);
	Response showLabel(String token);
	

}
