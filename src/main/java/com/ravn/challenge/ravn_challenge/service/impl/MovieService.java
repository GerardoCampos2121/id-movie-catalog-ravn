package com.ravn.challenge.ravn_challenge.service.impl;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

		Calendar calendar = Calendar.getInstance();
		Date currentDate = calendar.getTime();
        newMovie.setCreatedDate(currentDate);	
		
		return movieRepository.save(newMovie);
	}
	
	public Movie updateMovieOnDB(MovieDTO input,User currentUser,String operation, Integer idMovie) {
		
		if(operation.equals("Create") || operation.equals("update")) {
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
		}else if(operation.equals("rateMovie")){
			
			Optional<Movie> updateMovie = movieRepository.findById(idMovie);			
			
			updateMovie.get().setUserRate(currentUser);			
			
			Calendar calendar = Calendar.getInstance();
			Date currentDate = calendar.getTime();			
			updateMovie.get().setDateRate(currentDate);
			updateMovie.get().setRate("Excellent Movie");		
			return movieRepository.save(updateMovie.get());		
		}
		return null;
	}
	
	public void deleteMovie(Integer idMovie) {
		Movie deleteM = new Movie();
		deleteM.setId(idMovie);
		movieRepository.delete(deleteM);
	}
	
	public List<Movie> getAllRatedMovies(User userRate){
		return movieRepository.findByUserRate(userRate.getId());	
	}
	
}
