package com.ravn.challenge.ravn_challenge.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern.Flag;

import lombok.Data;

//@Data
public class LoginUserDto {

	//@NotEmpty(message = "Username is required.")
	//@Email(message = "The email address is invalid.", flags = { Flag.CASE_INSENSITIVE })
	private String username;

	//@NotEmpty(message = "The full name is required.")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

}
