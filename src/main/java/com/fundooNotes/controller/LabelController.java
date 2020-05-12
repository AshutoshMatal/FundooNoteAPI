package com.fundooNotes.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fundooNotes.dto.LabelDTO;
import com.fundooNotes.model.Response;
import com.fundooNotes.repository.LabelRepository;
import com.fundooNotes.service.LabelServiceImpl;
@RequestMapping("/label")
@RestController
@CrossOrigin(origins="*")
public class LabelController {
	@Autowired
	LabelRepository labelRepository;
	
	@Autowired
	LabelServiceImpl labelService;
	
	@PostMapping("/show")
	public ResponseEntity<Response> showLabel(@RequestHeader String token)
	{
		Response response=labelService.showLabel(token);
		return new ResponseEntity<Response>(response,HttpStatus.OK); 
	}
	@PostMapping("/addLabel")
	public ResponseEntity<Response> addLabel(@RequestHeader String token, @RequestBody LabelDTO labelDto)
	{
		Response response=labelService.addLabel(token,labelDto);
		return new ResponseEntity<Response>(response,HttpStatus.OK); 
	}
	@PostMapping("/editLabel/{id}")
	public ResponseEntity<Response> editLabel(@RequestHeader String token,@RequestBody LabelDTO labelDto,@PathVariable int id)
	{
		Response response=labelService.editLabel(token,labelDto,id);
		return new ResponseEntity<Response>(response,HttpStatus.OK); 
	}
	@PostMapping("/deleteLabel/{id}")
	public ResponseEntity<Response> deleteLabel(@RequestHeader String token, @PathVariable int id)
	{
		Response response=labelService.deleteLabel(token,id);
		return new ResponseEntity<Response>(response,HttpStatus.OK); 
	}

}
