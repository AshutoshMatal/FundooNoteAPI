package com.fundooNotes.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name = "UserDetails")
public class User 
{
	//VARIABLES
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String username;

	private String name;

	private String password;

	private String emailId;

	private String country;

	private long mobileNo;

	private boolean isValidate = false;

	@OneToMany(mappedBy = "user")
	private List<Note> noteList = new ArrayList<Note>();

	@OneToMany(mappedBy = "user")
	private List<Label> labelList = new ArrayList<Label>();

	public User() {
	}
	
	//GETTERS AND SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public String getEmailId()
	{
		return emailId;
	}
	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}
	public String getCountry() 
	{
		return country;
	}
	public void setCountry(String country)
	{
		this.country = country;
	}
	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public List<Note> getNoteList() {
		return noteList;
	}
	public void setNoteList(List<Note> noteList) {
		this.noteList = noteList;
	}
	public List<Label> getLabelList() {
		return labelList;
	}
	public void setLabelList(List<Label> labelList) {
		this.labelList = labelList;
	}

	public boolean getIsValidate() {
		return isValidate;
	}

	public void setIsValidate(boolean isValidate) {
		this.isValidate = isValidate;
	}

	public void setValidate(boolean isValidate) {
		this.isValidate = isValidate;
	}

}
