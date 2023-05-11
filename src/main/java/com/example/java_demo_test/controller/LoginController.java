package com.example.java_demo_test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.entity.Login;
import com.example.java_demo_test.service.ifs.LoginService;
import com.example.java_demo_test.vo.LoginRequest;
import com.example.java_demo_test.vo.LoginResponse;

import io.swagger.v3.oas.annotations.Hidden;

//import springfox.documentation.annotations.ApiIgnore;

@RestController
public class LoginController {
	
	@Autowired
	public LoginService loginService;

	@PostMapping(value = "addInfo")
	public LoginResponse addInfo(@RequestBody LoginRequest request) {
		return loginService.addInfo(request.getLogin());
		
	}
	
	@PostMapping(value = "activeAccount")
	public LoginResponse activeAccount(@RequestBody LoginRequest request) {
		return loginService.activeAccount(request.getActiveLogins());
		
	}
	
//	@ApiIgnore
	@Hidden
	@PostMapping(value = "loginAccount")
	public LoginResponse loginAccount(@RequestBody LoginRequest request) {
		return loginService.loginAccount(request.getLoginAccount());
	
	}
	
	@PostMapping(value = "findInfo")
	public LoginResponse findByCityContainingOrderByAgeAsc(@RequestBody LoginRequest request) {
		return loginService.findByCityContainingOrderByAgeAsc(request.getStr());
	}
	
	
	
}
