package com.example.java_demo_test.vo;

import java.time.LocalDateTime;

public class RegisterResponse {
	
	private LocalDateTime regTime;

	private String message;
	
	private String sessionId;
	
	private int verifyCode;

	
	public RegisterResponse(String sessionId, int verifyCode,String message) {
		super();
		this.message = message;
		this.sessionId = sessionId;
		this.verifyCode = verifyCode;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public int getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(int verifyCode) {
		this.verifyCode = verifyCode;
	}

	public RegisterResponse(LocalDateTime regTime, String message) {
		super();
		this.regTime = regTime;
		this.message = message;
	}

	public RegisterResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getRegTime() {
		return regTime;
	}

	public void setRegTime(LocalDateTime regTime) {
		this.regTime = regTime;
	}
	
	
}
