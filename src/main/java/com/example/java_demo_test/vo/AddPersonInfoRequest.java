package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.PersonInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddPersonInfoRequest {
	@JsonProperty("PersonInfos") 
	private List<PersonInfo> personInfos;

	public List<PersonInfo> getPersonInfos() {
		return personInfos;
	}

	public void setPersonInfos(List<PersonInfo> personInfos) {
		this.personInfos = personInfos;
	}
	
}
