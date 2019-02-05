package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RegistrationDetails {
	@Id
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String dateOfBirth;
	private String password;
	
	public RegistrationDetails() {
		this.userName = this.firstName = this.lastName = this.email = this.dateOfBirth = this.password = "";
	}
	
	public RegistrationDetails(String userName, String firstName, String lastName, String email, String dateOfBirth, String password) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
