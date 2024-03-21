package com.hexaware.CozyHeavenStay.customexception;
 
import org.springframework.http.HttpStatus;
public class BadRequestException extends RuntimeException{
	private HttpStatus sataus;
	private String message;
	public BadRequestException(HttpStatus sataus, String message) {
		this.sataus = sataus;
		this.message = message;
	}
	public BadRequestException(HttpStatus sataus, String message, String message2) {
		super(message);
		this.sataus = sataus;
		this.message = message2;
	}
	public BadRequestException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HttpStatus getSataus() {
		return sataus;
	}
	public void setSataus(HttpStatus sataus) {
		this.sataus = sataus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}