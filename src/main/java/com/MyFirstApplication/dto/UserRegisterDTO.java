package com.MyFirstApplication.dto;

	public class UserRegisterDTO 
	{
		private String username;
		private String name;
		private String password;
		private String emailId;
		private String country;

		public UserRegisterDTO(String username, String name, String password, String emailId, String country)
		{
			super();
			this.username = username;
			this.name = name;
			this.password = password;
			this.emailId = emailId;
			this.country = country;
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

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

	}


}
