package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.PersonInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddPersonInfoRequest {
	private String name;
	private String city;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty("PersonInfos") 
	private List<PersonInfo> personInfos;

	public List<PersonInfo> getPersonInfos() {
		return personInfos;
	}

	public void setPersonInfos(List<PersonInfo> personInfos) {
		this.personInfos = personInfos;
	}
	
	
	
}
