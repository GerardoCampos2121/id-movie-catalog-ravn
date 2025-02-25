package com.ravn.challenge.ravn_challenge.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ravn.challenge.ravn_challenge.dto.MovieDTO;
import com.ravn.challenge.ravn_challenge.dto.RatedMovieDTO;
import com.ravn.challenge.ravn_challenge.entities.Movie;
import com.ravn.challenge.ravn_challenge.entities.MovieCategory;
import com.ravn.challenge.ravn_challenge.entities.User;
import com.ravn.challenge.ravn_challenge.exception.AppException;
import com.ravn.challenge.ravn_challenge.repositories.MovieRepository;

@Service
public class MovieService {

	private final MovieRepository movieRepository;

	public MovieService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public Movie saveMovieOnDB(Integer idMovie, MovieDTO input, User currentUser, boolean isCreateOperation) {

		Movie recordMovie = new Movie();

		if (!isCreateOperation) {
			recordMovie = movieRepository.findById(idMovie).get();
			if (recordMovie == null) {
				throw new AppException("The idMovie doesn't belongs to a record on Movies!", HttpStatus.BAD_REQUEST,
						"400", "invalid Id Movie");
			}
		}

		recordMovie.setName(input.getName());
		recordMovie.setReleaseYear(input.getReleaseYear());
		recordMovie.setSynopsis(input.getSynopsis());
		recordMovie.setImage(null);
		MovieCategory movCat = new MovieCategory();
		movCat.setId(input.getIdCategory());
		recordMovie.setMovieCategory(movCat);
		recordMovie.setIdUser(currentUser); // always set user who saved or updated record

		Calendar calendar = Calendar.getInstance();
		Date currentDate = calendar.getTime();
		recordMovie.setCreatedDate(currentDate); // always set last update time or saving time

		return movieRepository.save(recordMovie);
	}

	public Movie rateMovie(String rateMovie, User currentUser, Integer idMovie) {

		Movie updateMovie = movieRepository.findById(idMovie).get();
		if (updateMovie == null) {
			throw new AppException("The idMovie doesn't belongs to a record on Movies!", HttpStatus.BAD_REQUEST, "400",
					"error rating Movie");
		}

		updateMovie.setUserRate(currentUser);

		Calendar calendar = Calendar.getInstance();
		Date currentDate = calendar.getTime();
		updateMovie.setDateRate(currentDate);
		updateMovie.setRate(rateMovie);
		return movieRepository.save(updateMovie);
	}

	public void removeRateMovie(Integer idMovie, User currentUser) {

		Movie updateMovie = movieRepository.findRatedMovie(idMovie, currentUser.getId());
		if (updateMovie == null) {
			throw new AppException("The idMovie doesn't belongs to a record on Movie or Movie was not rated by you!",
					HttpStatus.BAD_REQUEST, "400", "error rating Movie");
		}
		updateMovie.setUserRate(null);
		updateMovie.setDateRate(null);
		updateMovie.setRate(null);
		movieRepository.save(updateMovie);
	}

	public void deleteMovie(Integer idMovie) {

		Movie deleteMovie = movieRepository.findById(idMovie).get();
		if (deleteMovie == null) {
			throw new AppException("The idMovie doesn't belongs to a record on Movies!", HttpStatus.BAD_REQUEST, "400",
					"Error on delete Movie");
		}

		movieRepository.delete(deleteMovie);
	}

	public List<RatedMovieDTO> getAllRatedMovies(User userRate) {

		List<Movie> listMovies = movieRepository.findByUserRate(userRate.getId());
		if (listMovies == null || listMovies.size() == 0) {
			throw new AppException("You haven't rated a Movie!", HttpStatus.BAD_REQUEST, "400", "no records");
		}

		List<RatedMovieDTO> ratedMovies = new ArrayList<>();
		for (Movie record : listMovies) {
			RatedMovieDTO item = new RatedMovieDTO();
			item.setName(record.getName());
			item.setReleaseYear(record.getReleaseYear());
			item.setSynopsis(record.getSynopsis());
			item.setRate(record.getRate());
			item.setRatedDate(record.getDateRate());

			ratedMovies.add(item);
		}
		return ratedMovies;
	}

}
