package com.ravn.challenge.ravn_challenge.exception;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final HttpStatus httpStatus;
	
	private String errorCode;
	
	private String reasonStatus;
	
	private final transient Object body;
	
	
	public AppException(String message, HttpStatus httpStatus, String errorCode, String reasonStatus) {
		this(message,httpStatus,errorCode,reasonStatus,String.valueOf(httpStatus.value()),null);
	}
	
	
	public AppException(String message, HttpStatus httpStatus, String errorCode, String reasonStatus,Object body, Throwable exc) {
		super(message,exc);
		this.errorCode = errorCode;
		this.reasonStatus = reasonStatus;
		this.httpStatus = httpStatus;
		this.body = body;		
	}


	public String getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}


	public String getReasonStatus() {
		return reasonStatus;
	}


	public void setReasonStatus(String reasonStatus) {
		this.reasonStatus = reasonStatus;
	}


	public HttpStatus getHttpStatus() {
		return httpStatus;
	}


	public Object getBody() {
		return body;
	}	

}
