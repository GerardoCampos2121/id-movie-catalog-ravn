package com.ravn.challenge.ravn_challenge.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	public MoviesController(MovieService movieService, MovieCategoryService movieCatService,
			AuthenticationService authenticationService) {
		this.movieService = movieService;
		this.movieCatService = movieCatService;
		this.authenticationService = authenticationService;
	}

	@PostMapping("/create")
	public ResponseEntity<Movie> registerMovie(@RequestBody MovieDTO input) {
		User currentUser = authenticationService.checkIfUserIsAdmin();

		if (currentUser != null) {
			Movie newMovie = movieService.saveMovieOnDB(input, currentUser);
			return ResponseEntity.ok(newMovie);
		} else {
			return ResponseEntity.status(403).body(null);
		}

	}

	@PostMapping("/update")
	public ResponseEntity<Movie> updateMovie(@RequestBody MovieDTO input) {
		User currentUser = authenticationService.checkIfUserIsAdmin();

		if (currentUser != null) {
			Movie newMovie = movieService.saveMovieOnDB(input, currentUser);
			return ResponseEntity.ok(newMovie);
		} else {
			return ResponseEntity.status(403).body(null);
		}
	}

	@DeleteMapping("/delete/{idMovie}")
	public ResponseEntity<Void> deleteMovie(@PathVariable("idMovie") Integer idMovie) {
		User currentUser = authenticationService.checkIfUserIsAdmin();

		if (currentUser != null) {
			movieService.deleteMovie(idMovie);
			return ResponseEntity.ok(null);
		} else {
			return ResponseEntity.status(403).body(null);
		}

	}

	@PatchMapping("/rateMovie/{idMovie}")
	public ResponseEntity<Movie> rateMovie(@PathVariable("idMovie") Integer idMovie, @RequestBody MovieDTO input) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		User currentUser = (User) authentication.getPrincipal();
				
		Movie newMovie = movieService.updateMovieOnDB(input, currentUser, "rateMovie", idMovie);
		return ResponseEntity.ok(newMovie);
	}
	
	@GetMapping("allRatedMovies")
	public List<Movie> getAllRatedMovies() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		User currentUser = (User) authentication.getPrincipal();
		List<Movie> ratedMovies = movieService.getAllRatedMovies(currentUser);	
		System.out.println("size of ratedmovies: "+ratedMovies.size());
		return ratedMovies;
	}

}
