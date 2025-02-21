package com.ravn.challenge.ravn_challenge.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ravn.challenge.ravn_challenge.entities.Movie;



@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer>{
	
	Optional<Movie> findById(Integer id);
	
	//Optional<Movie> findByNameAndSynopsis(String name, String synopsis);

}
