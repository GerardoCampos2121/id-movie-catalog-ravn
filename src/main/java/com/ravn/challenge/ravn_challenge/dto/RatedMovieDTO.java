package com.ravn.challenge.ravn_challenge.dto;

import java.util.Date;

public class RatedMovieDTO {
	
    private String name;
	
	private Integer releaseYear;
	
	private String synopsis;
	
	private String rate;
	
	private Date ratedDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Date getRatedDate() {
		return ratedDate;
	}

	public void setRatedDate(Date ratedDate) {
		this.ratedDate = ratedDate;
	}	

}
