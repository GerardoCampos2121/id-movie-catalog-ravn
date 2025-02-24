package com.ravn.challenge.ravn_challenge.dto;

public class FormatErrorDTO {
	
	private String code = null;
	private String reason = null;
	private String message = null;
	private String status = null;
	private String dateTimeError;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDateTimeError() {
		return dateTimeError;
	}
	public void setDateTimeError(String dateTimeError) {
		this.dateTimeError = dateTimeError;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ResponseDTO { \n");
		
		sb.append("    code: ").append(code).append("\n");
		sb.append("    reason: ").append(reason).append("\n");
		sb.append("    message: ").append(message).append("\n");
		sb.append("    status: ").append(status).append("\n");
		sb.append("    timeError: ").append(dateTimeError).append("\n");
		
		return sb.toString();
	}

}
