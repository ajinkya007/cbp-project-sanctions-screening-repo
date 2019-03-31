package com.example.SansactionScreening.models;

import java.util.Date;

import javax.annotation.Generated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document(collection="users")
@JsonIgnoreProperties(allowGetters = true)
public class SS {
	
	@Id
    private String userName;
	
	private String emailAddress;
	private String password;

	public SS(){
		super();
	}
	
	public SS(String userName){
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SS [userName=" + userName + ", emailAddress=" + emailAddress + ", password=" + password + "]";
	}

	public SS(String userName, String emailAddress, String password) {
		super();
		this.userName = userName;
		this.emailAddress = emailAddress;
		this.password = password;
	}
	
	
	
	
}
