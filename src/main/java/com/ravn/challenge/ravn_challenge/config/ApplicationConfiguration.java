package com.ravn.challenge.ravn_challenge.config;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.ravn.challenge.ravn_challenge.repositories.UserRepository;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


//@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })

@Configuration
@EnableSwagger2
public class ApplicationConfiguration {
	
	 @Value("${swagger.host.url}")
	 private String hostUrl;

	private final UserRepository userRepository;

	public ApplicationConfiguration(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Bean
	UserDetailsService userDetailsService() throws UsernameNotFoundException {
		return username -> userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host(hostUrl)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

}
