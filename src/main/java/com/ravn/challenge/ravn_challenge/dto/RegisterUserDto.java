package com.ravn.challenge.ravn_challenge.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern.Flag;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class RegisterUserDto {

	@NotEmpty(message = "Username is required.")
	@Email(message = "The email address is invalid.", flags = { Flag.CASE_INSENSITIVE })
	private String username;

	@NotEmpty(message = "Password is required.")
	private String password;
	
	@NotEmpty(message = "name is required.")	
	private String name;
	
	@NotEmpty(message = "lastname is required.")
	private String lastname;
	
	@NotNull(message = "The idRol required.")
	@Positive(message = "The idRol must be greater than 0")
	private Integer idRol;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}	

}
