package com.example.java_demo_test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.service.ifs.PersonInfoService;
import com.example.java_demo_test.vo.AddPersonInfoRequest;
import com.example.java_demo_test.vo.GetPersonInfoRequest;
import com.example.java_demo_test.vo.GetPersonInfoResponse;
import com.example.java_demo_test.vo.PersonInfoResponse;

@RestController
public class PersonInfoController {
	
	@Autowired
	private PersonInfoService personInfoService;
	
	
	@PostMapping(value="add_person_info")
	public PersonInfoResponse addPersonInfo(@RequestBody AddPersonInfoRequest request) {
		return personInfoService.addPersonInfo(request.getPersonInfos());
	}
	
	
	@PostMapping(value="get_person_info_by_id")
	public GetPersonInfoResponse getPersonInfoById(@RequestBody GetPersonInfoRequest request) {
		return personInfoService.getPersonInfoById(request.getId());
	}
	
	@GetMapping(value = "all_person_info")
	public GetPersonInfoResponse getAllPersonInfo(@RequestBody GetPersonInfoRequest request) {
		return personInfoService.getAllPersonInfo();
	}
	
	@PostMapping(value = "find_by_age")
	public GetPersonInfoResponse findByAgeBetweenOrderByAgeDesc(@RequestBody GetPersonInfoRequest request) {
		return personInfoService.findByAgeBetweenOrderByAgeDesc(request.getAge(),request.getAge1());
	}

}
