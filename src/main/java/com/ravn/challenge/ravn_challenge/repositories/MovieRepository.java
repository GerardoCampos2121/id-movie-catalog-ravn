package com.ravn.challenge.ravn_challenge.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ravn.challenge.ravn_challenge.entities.Movie;



@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer>{
	
	Optional<Movie> findById(Integer id);
	
	@Query(value = "Select * from Movie where user_rate = :userRate order by name asc", nativeQuery = true)
	List<Movie> findByUserRate(Integer userRate);
	


}
