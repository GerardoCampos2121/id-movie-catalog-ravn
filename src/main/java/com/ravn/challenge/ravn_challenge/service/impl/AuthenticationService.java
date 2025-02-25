package com.ravn.challenge.ravn_challenge.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ravn.challenge.ravn_challenge.dto.LoginUserDto;
import com.ravn.challenge.ravn_challenge.dto.RegisterUserDto;
import com.ravn.challenge.ravn_challenge.entities.Rol;
import com.ravn.challenge.ravn_challenge.entities.User;
import com.ravn.challenge.ravn_challenge.exception.AppException;
import com.ravn.challenge.ravn_challenge.repositories.UserRepository;

@Service
public class AuthenticationService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	public AuthenticationService(UserRepository userRepository, AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User signup(RegisterUserDto input) {
		User user = new User();
		user.setName(input.getName());
		user.setLastname(input.getLastname());
		user.setUsername(input.getUsername());
		user.setPassword(passwordEncoder.encode(input.getPassword()));
		Rol userRol = new Rol();
		userRol.setId(input.getIdRol());
		user.setRol(userRol);

		return userRepository.save(user);
	}

	public User authenticate(LoginUserDto input) {

		if (input != null) {
			if (input.getUsername() == null || input.getUsername().isEmpty()) {
				throw new AppException("username cannot be null or empty", HttpStatus.BAD_REQUEST, "400",
						"username required");
			} else if (input.getPassword() == null || input.getPassword().isEmpty()) {
				throw new AppException("password cannot be null or empty", HttpStatus.BAD_REQUEST, "400",
						"password required");
			}
		}

		try {

			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword()));
			return userRepository.findByUsername(input.getUsername()).orElseThrow();

		} catch (Exception x) {
			throw new AppException("Error trying to authenticate, try later",HttpStatus.INTERNAL_SERVER_ERROR,"500","authenticate");

		}

	}	

	public User checkIfUserIsAdmin() {
		
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			User currentUser = (User) authentication.getPrincipal();
			Rol rol = new Rol();
			rol.setId(1);// admin this needs to be changed later

			if (currentUser.getRol().getId().equals(rol.getId()))
				return currentUser;
			else
				return null;
			
		}catch(Exception x) {
			throw new AppException("No valid token",HttpStatus.BAD_REQUEST,String.valueOf(HttpStatus.BAD_REQUEST.value()),"token");
			
		}

	}

}
