package com.ravn.challenge.ravn_challenge.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ravn.challenge.ravn_challenge.entities.User;
import com.ravn.challenge.ravn_challenge.repositories.UserRepository;

@Service
public class UserServiceImpl {
	
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	
	
	public UserServiceImpl(PasswordEncoder passwordEncoder,UserRepository userRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}
	
	
	public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }	
	
	public String encodePass(String pass) {
		return passwordEncoder.encode(pass);		
	}
	
	

}
