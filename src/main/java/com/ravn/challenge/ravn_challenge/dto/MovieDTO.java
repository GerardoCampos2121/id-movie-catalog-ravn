package com.ravn.challenge.ravn_challenge.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;


@Data
public class MovieDTO {
	
	@NotEmpty(message = "Name of Movie is required.")	
	private String name;
	
	private Integer releaseYear;
	
	private String synopsis;
	
	private String image;
	
	@NotNull(message = "The idCategory required.")
	@Positive(message = "The idCategory must be greater than 0")
	private Integer idCategory;	
	

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
	}
	

}
