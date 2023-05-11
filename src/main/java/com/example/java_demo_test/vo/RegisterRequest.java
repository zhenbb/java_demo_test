package com.example.java_demo_test.vo;

import com.example.java_demo_test.entity.Register;

public class RegisterRequest {

	private String account;
	
	private String pwd;

	private int verifyCode;
	
	public int getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(int verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}



}
