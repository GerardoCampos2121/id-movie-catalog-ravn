package com.ravn.challenge.ravn_challenge.dto;

public class SearcMovieDTO {
	
	private String name;
	
	private String synopsis;
	
	private Integer moviesPerPage;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Integer getMoviesPerPage() {
		return moviesPerPage;
	}

	public void setMoviesPerPage(Integer moviesPerPage) {
		this.moviesPerPage = moviesPerPage;
	}
	
	

}
