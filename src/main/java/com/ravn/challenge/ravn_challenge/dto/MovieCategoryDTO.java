package com.ravn.challenge.ravn_challenge.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MovieCategoryDTO {
	
	@NotEmpty(message = "Category name is required.")
	private String movieCategory;

	public String getMovieCategory() {
		return movieCategory;
	}

	public void setMovieCategory(String movieCategory) {
		this.movieCategory = movieCategory;
	}
	
	
	
	

}
