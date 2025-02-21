package com.ravn.challenge.ravn_challenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravn.challenge.ravn_challenge.dto.MovieCategoryDTO;
import com.ravn.challenge.ravn_challenge.entities.MovieCategory;
import com.ravn.challenge.ravn_challenge.entities.User;
import com.ravn.challenge.ravn_challenge.service.impl.AuthenticationService;
import com.ravn.challenge.ravn_challenge.service.impl.MovieCategoryService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/categorymovie")
@RestController
@Slf4j
public class MovieCatController {
	
	private final MovieCategoryService movieCatService;
	
	private final AuthenticationService authenticationService;
	
	
	public MovieCatController(MovieCategoryService movieCatService,AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
		this.movieCatService = movieCatService;
	}
	
	
	@PostMapping("/create")
    public ResponseEntity<MovieCategory> registerMovieCat(@RequestBody MovieCategoryDTO input) {
		
		User currentUser = authenticationService.checkIfUserIsAdmin();
		if(currentUser != null) {
			MovieCategory saveMovCat = movieCatService.saveOnDB(input);
			return ResponseEntity.ok(saveMovCat);
		}else {
			return ResponseEntity.status(403).body(null);
		}
	}

}
