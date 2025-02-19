package com.ravn.challenge.ravn_challenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

@RequestMapping("/movie")
@RestController
@Slf4j
public class MoviesController {

	private final MovieCategoryService movieCatService;

	private final AuthenticationService authenticationService;

	public MoviesController(MovieCategoryService movieCatService, AuthenticationService authenticationService) {
		this.movieCatService = movieCatService;
		this.authenticationService = authenticationService;
	}

	@PostMapping("/moviecategory")
	public ResponseEntity<MovieCategory> registerMovieCat(@RequestBody MovieCategoryDTO input) {
		
		if(authenticationService.checkIfUserIsAdmin()) {
			MovieCategory saveMovCat = movieCatService.saveOnDB(input);
			return ResponseEntity.ok(saveMovCat);
		}else {
			return ResponseEntity.status(403).body(null);
		}
	}

}
