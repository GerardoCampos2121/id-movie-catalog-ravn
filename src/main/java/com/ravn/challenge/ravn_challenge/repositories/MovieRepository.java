package com.ravn.challenge.ravn_challenge.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ravn.challenge.ravn_challenge.entities.Movie;



@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer>{
	
	Optional<Movie> findById(Integer id);
	
	@Query(value = "Select * from Movie where user_rate = :userRate order by name asc", nativeQuery = true)
	List<Movie> findByUserRate(Integer userRate);
	
	@Query(value = "Select * from Movie where id_movie = :idMovie and user_rate = :userRate", nativeQuery = true)
	Movie findRatedMovie(Integer idMovie,Integer userRate);
	
	@Query(value = "Select * from Movie where name like ':name %' and synopsis like ':synopsis %'", nativeQuery = true)
	List<Movie> dynamicSearchMovies(String name,String synopsis,Pageable pageable);
	


}
