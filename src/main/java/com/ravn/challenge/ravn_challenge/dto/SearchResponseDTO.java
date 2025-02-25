package com.ravn.challenge.ravn_challenge.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Data;

@Data
public class SearchResponseDTO<T> {
	
	 private boolean success;
	    private LocalDateTime timestamp;
	    private String message;
	    private T data;

	    public SearchResponseDTO(boolean success, String message, T data) {
	        this.success = success;
	        this.timestamp = LocalDateTime.now();
	        this.message = message;
	        this.data = data;
	    }

	    public static <T> ResponseEntity<SearchResponseDTO<T>> success(HttpStatus status, T data) {
	        return ResponseEntity
	                .status(HttpStatus.OK)
	                .body(new SearchResponseDTO<>(true, HttpStatus.OK.name(), data));
	    }

	    public static <T> ResponseEntity<SearchResponseDTO<T>> error(HttpStatus status, T data) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new SearchResponseDTO<>(false, HttpStatus.INTERNAL_SERVER_ERROR.name(), data));
	    }

}
