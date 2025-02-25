package com.ravn.challenge.ravn_challenge.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ravn.challenge.ravn_challenge.dto.MovieDTO;
import com.ravn.challenge.ravn_challenge.dto.RateDTO;
import com.ravn.challenge.ravn_challenge.dto.RatedMovieDTO;
import com.ravn.challenge.ravn_challenge.dto.ResponseDTO;
import com.ravn.challenge.ravn_challenge.entities.Movie;
import com.ravn.challenge.ravn_challenge.entities.User;
import com.ravn.challenge.ravn_challenge.exception.AppException;
import com.ravn.challenge.ravn_challenge.service.impl.AuthenticationService;
import com.ravn.challenge.ravn_challenge.service.impl.MovieCategoryService;
import com.ravn.challenge.ravn_challenge.service.impl.MovieService;
import lombok.extern.slf4j.Slf4j;

@Validated
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
	public ResponseEntity<ResponseDTO> registerMovie(@Valid @RequestBody MovieDTO input) {
		User currentUser = authenticationService.checkIfUserIsAdmin();

		if (currentUser != null) {
			Movie newMovie = movieService.saveMovieOnDB(null, input, currentUser, true);
			ResponseDTO response = new ResponseDTO();
			response.setCode("200");
			response.setMessage("New record of movie save! Name: " + newMovie.getName());
			return ResponseEntity.ok(response);
		} else {
			throw new AppException("user not authorized to register a movie", HttpStatus.BAD_REQUEST,
					"" + HttpStatus.BAD_REQUEST.value(), "user not allowed");
		}

	}

	@PostMapping("/update/{idMovie}")
	public ResponseEntity<ResponseDTO> updateMovie(@PathVariable("idMovie") Integer idMovie,
			@RequestBody MovieDTO input) {
		User currentUser = authenticationService.checkIfUserIsAdmin();

		if (currentUser != null) {
			if (idMovie == null || idMovie < 0) {
				throw new AppException("idMovie is not valid id", HttpStatus.BAD_REQUEST, "400", "invalid idMovie");
			}
			Movie updateMovie = movieService.saveMovieOnDB(idMovie, input, currentUser, false);
			ResponseDTO response = new ResponseDTO();
			response.setCode("200");
			response.setMessage("Record of movie with name " + updateMovie.getName() + " updated!");
			return ResponseEntity.ok(response);
		} else {
			throw new AppException("user not authorized to update a movie", HttpStatus.BAD_REQUEST,
					"" + HttpStatus.BAD_REQUEST.value(), "user not allowed");
		}
	}
	
	@PatchMapping("/rateMovie/{idMovie}")
	public ResponseEntity<ResponseDTO> rateMovie(@PathVariable("idMovie") Integer idMovie, @RequestBody RateDTO rateMovie) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		User currentUser = (User) authentication.getPrincipal();
		Movie ratedMovie = movieService.rateMovie(rateMovie.getRate(), currentUser, idMovie);
		
		ResponseDTO response = new ResponseDTO();
		response.setCode("200");
		response.setMessage("Movie was rated with success!");
		return ResponseEntity.ok(response);	
		
	}
	
	@PatchMapping("/removeRateMovie/{idMovie}")
	public ResponseEntity<ResponseDTO> removeRateMovie(@PathVariable("idMovie") Integer idMovie) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		User currentUser = (User) authentication.getPrincipal();

				
		movieService.removeRateMovie(idMovie,currentUser);
		
		ResponseDTO response = new ResponseDTO();
		response.setCode("200");
		response.setMessage("Rate was removed with success!");
		return ResponseEntity.ok(response);	
	}
	

	@GetMapping("allRatedMovies")
	public List<RatedMovieDTO> getAllRatedMovies() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		User currentUser = (User) authentication.getPrincipal();
		List<RatedMovieDTO> ratedMovies = movieService.getAllRatedMovies(currentUser);
		return ratedMovies;
	}
	
	
	@DeleteMapping("/delete/{idMovie}")
	public ResponseEntity<ResponseDTO> deleteMovie(@PathVariable("idMovie") Integer idMovie) {
		User currentUser = authenticationService.checkIfUserIsAdmin();

		if (currentUser != null) {

			if (idMovie == null || idMovie < 0) {
				throw new AppException("idMovie is not valid id", HttpStatus.BAD_REQUEST, "400", "invalid idMovie");
			}

			movieService.deleteMovie(idMovie);

			ResponseDTO response = new ResponseDTO();
			response.setCode("200");
			response.setMessage("Record of movie with id " + idMovie + " was removed with success!");

			return ResponseEntity.ok(response);
		} else {
			throw new AppException("user not authorized to update a movie", HttpStatus.BAD_REQUEST,
					"" + HttpStatus.BAD_REQUEST.value(), "user not allowed");
		}

	}


}
