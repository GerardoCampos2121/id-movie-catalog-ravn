package com.ravn.challenge.ravn_challenge.dto;


public class ResponseDTO {
	
	private String code = null;	
	private String message = null;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("class ResponseDTO { \n");
		
		sb.append("    code: ").append(code).append("\n");		
		sb.append("    message: ").append(message).append("\n");		
		
		return sb.toString();
	}
			

}
