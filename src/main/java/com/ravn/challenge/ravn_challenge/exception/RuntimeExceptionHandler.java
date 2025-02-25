package com.ravn.challenge.ravn_challenge.exception;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import com.ravn.challenge.ravn_challenge.dto.FormatErrorDTO;
import io.jsonwebtoken.ExpiredJwtException;



@ControllerAdvice
public class RuntimeExceptionHandler{

	@ExceptionHandler({AppException.class})
	protected ResponseEntity<Object> handleMethodArgumentNotValid(AppException ex,
			HttpServletRequest request, HandlerMethod handlerMethod) {
		
		FormatErrorDTO error = new FormatErrorDTO();
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request){
		
		BindingResult result = ex.getBindingResult();
		List<FieldError> errors = result.getFieldErrors();
		StringBuilder sb = new StringBuilder();
		sb.append(String.valueOf(result.getFieldErrorCount())).append("errors");
		
		int count = 0;
		for(FieldError fieldError: errors) {
			sb.append(" Error ").append(String.valueOf(++count)).append(": Invalid ").append(fieldError.getField());			
		}		
		
		FormatErrorDTO error = new FormatErrorDTO();
		error.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
		error.setMessage(sb.toString());
		error.setReason("Some parameters are null or empty");
		error.setStatus(HttpStatus.BAD_REQUEST.name());
		
		
		ResponseEntity<Object> response = new ResponseEntity<>(error,new HttpHeaders(),HttpStatus.BAD_REQUEST);
		return response;
	}
	
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<Object> handleExpiredToken(ExpiredJwtException ex, HttpServletRequest request){
		
		FormatErrorDTO error = new FormatErrorDTO();
		error.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
		error.setMessage("Token is not valid or is empty or expired");
		error.setReason("Invalid token");
		error.setStatus(HttpStatus.BAD_REQUEST.name());
		
		
		ResponseEntity<Object> response = new ResponseEntity<>(error,new HttpHeaders(),HttpStatus.BAD_REQUEST);
		return response;

	}
	
	@ExceptionHandler(MissingPathVariableException.class)
	public ResponseEntity<Object> handleExpiredToken(MissingPathVariableException ex, HttpServletRequest request){
		FormatErrorDTO error = new FormatErrorDTO();
		error.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
		error.setMessage("Path parameter to find Movie is required!");
		error.setReason("Invalid id Movie");
		error.setStatus(HttpStatus.BAD_REQUEST.name());
		
		
		ResponseEntity<Object> response = new ResponseEntity<>(error,new HttpHeaders(),HttpStatus.BAD_REQUEST);
		return response;
		
	}
	

}
