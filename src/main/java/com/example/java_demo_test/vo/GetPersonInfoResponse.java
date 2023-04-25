package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.PersonInfo;

public class GetPersonInfoResponse {
	
	private PersonInfo personInfo;
	
	private String message;
	
	private List<PersonInfo> personInfos;
	
	private int age;
	

	
	public List<PersonInfo> getPersonInfos() {
		return personInfos;
	}

	public void setPersonInfos(List<PersonInfo> personInfos) {
		this.personInfos = personInfos;
	}

	public GetPersonInfoResponse( List<PersonInfo> personInfos,String message) {
		super();
		this.message = message;
		this.personInfos = personInfos;
	}

	public GetPersonInfoResponse() {
		super();

	}

	public GetPersonInfoResponse(PersonInfo personInfo, String message) {
		super();
		this.personInfo = personInfo;
		this.message = message;
	}
	

	public GetPersonInfoResponse(String message) {
		super();
		this.message = message;
	}

	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	
}
