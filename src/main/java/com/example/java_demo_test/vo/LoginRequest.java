package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.Login;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
	
	@JsonProperty("addInfo")
	List<Login> login;
	
	@JsonProperty("activeAccount")
	List<Login> activeLogins;
	
	@JsonProperty("loginAccount")
	List<Login> loginAccount;
	
	@JsonProperty("findInfo")
	List<Login> findInfo;

	private String str;
	
	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public List<Login> getFindInfo() {
		return findInfo;
	}

	public void setFindInfo(List<Login> findInfo) {
		this.findInfo = findInfo;
	}

	public List<Login> getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(List<Login> loginAccount) {
		this.loginAccount = loginAccount;
	}

	public List<Login> getActiveLogins() {
		return activeLogins;
	}

	public void setActiveLogins(List<Login> activeLogins) {
		this.activeLogins = activeLogins;
	}

	public List<Login> getLogin() {
		return login;
	}

	public void setLogin(List<Login> login) {
		this.login = login;
	}




	

}
