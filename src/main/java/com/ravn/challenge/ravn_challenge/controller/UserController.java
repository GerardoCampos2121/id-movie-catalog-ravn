package com.ravn.challenge.ravn_challenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravn.challenge.ravn_challenge.dto.LoginResponseDTO;
import com.ravn.challenge.ravn_challenge.dto.LoginUserDto;
import com.ravn.challenge.ravn_challenge.dto.RegisterUserDto;
import com.ravn.challenge.ravn_challenge.entities.User;
import com.ravn.challenge.ravn_challenge.service.impl.AuthenticationService;
import com.ravn.challenge.ravn_challenge.service.impl.JwtService;

@RequestMapping("/auth")
@RestController
public class UserController {

	private final JwtService jwtService;

	private final AuthenticationService authenticationService;

	public UserController(JwtService jwtService, AuthenticationService authenticationService) {
		this.jwtService = jwtService;
		this.authenticationService = authenticationService;
	}

	@PostMapping("/signup")
	public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
		User registeredUser = authenticationService.signup(registerUserDto);

		return ResponseEntity.ok(registeredUser);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody LoginUserDto loginUserDto) {
		User authenticatedUser = authenticationService.authenticate(loginUserDto);

		String jwtToken = jwtService.generateToken(authenticatedUser);

		LoginResponseDTO loginResponse = new LoginResponseDTO();
		loginResponse.setToken(jwtToken);
		loginResponse.setExpiresIn(jwtService.getExpirationTime());

		return ResponseEntity.ok(loginResponse);
	}

}
