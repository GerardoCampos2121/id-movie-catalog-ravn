package com.ravn.challenge.ravn_challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ravn.challenge.ravn_challenge.entities.User;
import com.ravn.challenge.ravn_challenge.service.impl.UserServiceImpl;

@RestController
public class MoviesController {	
	
	private UserServiceImpl userService;
	
	public MoviesController(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	@GetMapping("/greeting")
	public String welcome(@RequestParam(value = "name", defaultValue = "World") String name) {		
		return String.format("Hello, your pass encoded is  %s ", userService.encodePass(name)); 
	}
	
	/*@GetMapping("allusers")
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }*/
	
}
