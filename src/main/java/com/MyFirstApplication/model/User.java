package com.MyFirstApplication.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "UserDetails")
public class User 
{
	//VARIABLES
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long id;
	@NotEmpty(message = "Please provide your user name")
	private String username;
	@NotEmpty(message = "Please provide your name")
	private String name;
	@Transient
	private String password;
	@Email(message = "Please provide a valid e-mail")
	@NotEmpty(message = "Please provide an e-mail")
	private String emailId;
	@NotEmpty(message = "Please provide your Country name")
	private String country;
	@Pattern(regexp = "([0-9]{10})")
	private long mobileNo;
	
	public User() {
	}
	public User(String username, String name, String password, String emailId, String country, long mobileNo) {
		super();
		this.username = username;
		this.name = name;
		this.password = password;
		this.emailId = emailId;
		this.country = country;
		this.mobileNo = mobileNo;
	}
	
	//GETTERS AND SETTERS

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

}
