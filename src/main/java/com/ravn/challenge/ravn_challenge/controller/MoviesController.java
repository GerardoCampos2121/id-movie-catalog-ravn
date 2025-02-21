package com.ravn.challenge.ravn_challenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ravn.challenge.ravn_challenge.dto.MovieCategoryDTO;
import com.ravn.challenge.ravn_challenge.dto.MovieDTO;
import com.ravn.challenge.ravn_challenge.entities.Movie;
import com.ravn.challenge.ravn_challenge.entities.MovieCategory;
import com.ravn.challenge.ravn_challenge.entities.User;
import com.ravn.challenge.ravn_challenge.service.impl.AuthenticationService;
import com.ravn.challenge.ravn_challenge.service.impl.MovieCategoryService;
import com.ravn.challenge.ravn_challenge.service.impl.MovieService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/movie")
@RestController
@Slf4j
public class MoviesController {
	
	private final MovieService movieService;

	private final MovieCategoryService movieCatService;

	private final AuthenticationService authenticationService;

	public MoviesController(MovieService movieService,MovieCategoryService movieCatService, AuthenticationService authenticationService) {
		this.movieService = movieService;
		this.movieCatService = movieCatService;
		this.authenticationService = authenticationService;
	}	
	
	@PostMapping("/create")
	public ResponseEntity<Movie> registerMovie(@RequestBody MovieDTO input){
		User currentUser = authenticationService.checkIfUserIsAdmin();		
		
		if(currentUser != null) {
		Movie newMovie = movieService.saveMovieOnDB(input,currentUser);
		return ResponseEntity.ok(newMovie);
		}else {
			return ResponseEntity.status(403).body(null);
		}
		
	}

}
