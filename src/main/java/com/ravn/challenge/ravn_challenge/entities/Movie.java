package com.ravn.challenge.ravn_challenge.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "movie")
@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, name = "id_movie")
	private Integer Id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = true, name = "release_year")
	private Integer releaseYear;

	@Column(nullable = true)
	private String synopsis;

	@Lob
	@Column(nullable = true, columnDefinition = "MEDIUMBLOB", name = "image_poster")
	private String image;

	@OneToOne
	@JoinColumn(name = "id_category", nullable = false)
	private MovieCategory movieCategory;

	@OneToOne
	@JoinColumn(name = "id_user", nullable = false)
	private User idUser;

	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	@OneToOne
	@JoinColumn(name = "user_rate", nullable = true)
	private User userRate;

	@Column(name = "date_rate", nullable = true)
	private Date dateRate;

	@Column(nullable = true)
	private String rate;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

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

	public MovieCategory getMovieCategory() {
		return movieCategory;
	}

	public void setMovieCategory(MovieCategory movieCategory) {
		this.movieCategory = movieCategory;
	}

	public User getIdUser() {
		return idUser;
	}

	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getUserRate() {
		return userRate;
	}

	public void setUserRate(User userRate) {
		this.userRate = userRate;
	}

	public Date getDateRate() {
		return dateRate;
	}

	public void setDateRate(Date dateRate) {
		this.dateRate = dateRate;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

}
