package com.ravn.challenge.ravn_challenge.exception;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;

import com.ravn.challenge.ravn_challenge.dto.ResponseDTO;



@ControllerAdvice
public class RuntimeExceptionHandler {

	@ExceptionHandler({AppException.class})
	protected ResponseEntity<Object> handleMethodArgumentNotValid(AppException ex,
			HttpServletRequest request, HandlerMethod handlerMethod) {
		
		ResponseDTO error = new ResponseDTO();
		error.setCode(ex.getErrorCode());
		error.setMessage(ex.getMessage());
		error.setReason(ex.getReasonStatus());
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy"); 

        String str = ft.format(new Date()); 
        error.setDateTimeError(str);
        error.setStatus(HttpStatus.BAD_REQUEST.name());

		ResponseEntity<Object> response = new ResponseEntity<>(error,new HttpHeaders(),HttpStatus.BAD_REQUEST);
	    
	    return response;
	}	
	

}
