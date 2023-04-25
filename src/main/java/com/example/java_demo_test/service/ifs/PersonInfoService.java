package com.example.java_demo_test.service.ifs;

import java.util.List;

import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.vo.GetPersonInfoResponse;
import com.example.java_demo_test.vo.PersonInfoResponse;

public interface PersonInfoService {

	public PersonInfoResponse addPersonInfo(List<PersonInfo>personInfos);
	
	public GetPersonInfoResponse getPersonInfoById(String id);
	
	public GetPersonInfoResponse getAllPersonInfo();
	
	public GetPersonInfoResponse findByAgeGreaterThan(int age);
	
	public GetPersonInfoResponse findByAgeLessThanEqualOrderByAgeAsc(int age);
	
	public GetPersonInfoResponse findByAgeBetweenOrderByAgeDesc(int age ,int age1);
	
}
