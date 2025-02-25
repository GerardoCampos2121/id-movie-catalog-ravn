package com.ravn.challenge.ravn_challenge.controller;



import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ravn.challenge.ravn_challenge.dto.LoginResponseDTO;
import com.ravn.challenge.ravn_challenge.dto.LoginUserDto;
import com.ravn.challenge.ravn_challenge.dto.RegisterUserDto;
import com.ravn.challenge.ravn_challenge.dto.ResponseDTO;
import com.ravn.challenge.ravn_challenge.entities.User;
import com.ravn.challenge.ravn_challenge.exception.AppException;
import com.ravn.challenge.ravn_challenge.service.impl.AuthenticationService;
import com.ravn.challenge.ravn_challenge.service.impl.JwtService;
import com.ravn.challenge.ravn_challenge.util.Validation;


@Validated
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
	public ResponseEntity<ResponseDTO> register(@Valid @RequestBody RegisterUserDto registerUserDto) {
		
		if(registerUserDto.getUsername() == null || !Validation.validate(registerUserDto.getUsername())) {
			throw new AppException("Please enter a valid email",HttpStatus.BAD_REQUEST,"400","email");			
		}
		
		User registeredUser = authenticationService.signup(registerUserDto);
		
		ResponseDTO response = new ResponseDTO();
		response.setCode("200");
		response.setMessage("User "+registeredUser.getName() + " "+registeredUser.getLastname()+" registered successfully");


		return ResponseEntity.ok(response);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> authenticate(@Valid @RequestBody LoginUserDto loginUserDto) {
		
		if(loginUserDto.getUsername() == null || !Validation.validate(loginUserDto.getUsername())) {
			throw new AppException("Please enter a valid email",HttpStatus.BAD_REQUEST,"400","email");			
		}
		
		User authenticatedUser = authenticationService.authenticate(loginUserDto);

		String jwtToken = jwtService.generateToken(authenticatedUser);

		LoginResponseDTO loginResponse = new LoginResponseDTO();
		loginResponse.setToken(jwtToken);
		loginResponse.setExpiresIn(jwtService.getExpirationTime());

		return ResponseEntity.ok(loginResponse);
	}

}
