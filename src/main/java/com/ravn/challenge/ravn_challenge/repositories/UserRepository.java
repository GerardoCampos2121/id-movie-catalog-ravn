package com.ravn.challenge.ravn_challenge.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ravn.challenge.ravn_challenge.entities.Rol;
import com.ravn.challenge.ravn_challenge.entities.User;


@Repository
public interface UserRepository extends CrudRepository<User, Integer>  {
	
	Optional<User> findByUsername(String username);
	
	Optional<User> findByRol(Rol rol);
	
	

}
