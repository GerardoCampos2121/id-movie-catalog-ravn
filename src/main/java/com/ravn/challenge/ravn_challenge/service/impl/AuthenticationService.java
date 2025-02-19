package com.ravn.challenge.ravn_challenge.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ravn.challenge.ravn_challenge.dto.LoginUserDto;
import com.ravn.challenge.ravn_challenge.dto.RegisterUserDto;
import com.ravn.challenge.ravn_challenge.entities.Rol;
import com.ravn.challenge.ravn_challenge.entities.User;
import com.ravn.challenge.ravn_challenge.repositories.UserRepository;

@Service
public class AuthenticationService {
	
private final UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        User user = new User();
        user.setName(input.getName());
        user.setLastname(input.getLastname());
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        Rol userRol = new Rol();
        userRol.setId(input.getIdRol());
        user.setRol(userRol);             

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsername(input.getUsername())
                .orElseThrow();
    }

}
