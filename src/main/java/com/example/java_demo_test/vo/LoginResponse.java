package com.example.java_demo_test.vo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;

import com.example.java_demo_test.entity.Login;

public class LoginResponse {

	private Login login;
	private String message;
	private List<Login> logins;

	
	public LoginResponse( List<Login> logins,String message) {
		super();
		this.message = message;
		this.logins = logins;
	}
	public List<Login> getLogins() {
		return logins;
	}
	public void setLogins(List<Login> logins) {
		this.logins = logins;
	}
	public LoginResponse(String message) {
		super();
		this.message = message;
	}
	public LoginResponse(Login login, String message) {
		super();
		this.login = login;
		this.message = message;
	}
	public LoginResponse() {
		super();

	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
