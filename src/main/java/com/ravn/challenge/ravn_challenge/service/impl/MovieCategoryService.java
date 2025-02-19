package com.ravn.challenge.ravn_challenge.service.impl;

import org.springframework.stereotype.Service;
import com.ravn.challenge.ravn_challenge.dto.MovieCategoryDTO;
import com.ravn.challenge.ravn_challenge.entities.MovieCategory;
import com.ravn.challenge.ravn_challenge.repositories.MovieCategoryRepository;


@Service
public class MovieCategoryService {
	
	private final MovieCategoryRepository movieCatRepository;
	
	public MovieCategoryService(MovieCategoryRepository movieCatRepository) {
		this.movieCatRepository = movieCatRepository;		
	}
	
	public MovieCategory saveOnDB(MovieCategoryDTO input) {
		MovieCategory newMovieCat = new MovieCategory();
		newMovieCat.setName(input.getMovieCategory());
		return movieCatRepository.save(newMovieCat);		
	}

}
