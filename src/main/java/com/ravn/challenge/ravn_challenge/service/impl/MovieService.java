package com.ravn.challenge.ravn_challenge.service.impl;


import java.util.Date;
import org.springframework.stereotype.Service;
import com.ravn.challenge.ravn_challenge.dto.MovieDTO;
import com.ravn.challenge.ravn_challenge.entities.Movie;
import com.ravn.challenge.ravn_challenge.entities.MovieCategory;
import com.ravn.challenge.ravn_challenge.entities.User;
import com.ravn.challenge.ravn_challenge.repositories.MovieRepository;

@Service
public class MovieService {

	private final MovieRepository movieRepository;
	
	public MovieService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;		
	}
	
	public Movie saveMovieOnDB(MovieDTO input,User currentUser) {
		Movie newMovie = new Movie();
		newMovie.setName(input.getName());
		newMovie.setReleaseYear(input.getReleaseYear());
		newMovie.setSynopsis(input.getSynopsis());
		newMovie.setImage(null);
		MovieCategory movCat = new MovieCategory();
		movCat.setId(input.getIdCategory());
		newMovie.setMovieCategory(movCat);		
		newMovie.setIdUser(currentUser);		
		newMovie.setCreatedDate(new Date());
		
		return movieRepository.save(newMovie);
	}
}
