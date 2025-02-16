package com.ravn.challenge.ravn_challenge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoviesController {
	
	@GetMapping("/greeting")
	public String welcome(@RequestParam(value = "name", defaultValue = "World") String name) {		
		return String.format("Hello, %s ", name); 
	}

}
