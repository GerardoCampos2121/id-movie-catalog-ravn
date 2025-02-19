package com.ravn.challenge.ravn_challenge.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ravn.challenge.ravn_challenge.entities.MovieCategory;

@Repository
public interface MovieCategoryRepository extends CrudRepository<MovieCategory,Integer> {
	
	Optional<MovieCategory> findById(Integer id);

}
