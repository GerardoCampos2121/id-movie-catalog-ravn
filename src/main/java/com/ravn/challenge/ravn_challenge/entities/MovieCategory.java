package com.ravn.challenge.ravn_challenge.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "	movie_category")
@Entity
public class MovieCategory {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false,name="id_category")
	private Integer Id;
	
	@Column(nullable = false, name = "category_name")	
	private String name;

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

	@Override
	public String toString() {
		return "MovieCategory [Id=" + Id + ", name=" + name + "]";
	}
	

}
