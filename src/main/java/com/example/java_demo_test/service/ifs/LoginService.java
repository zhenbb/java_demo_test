package com.example.java_demo_test.service.ifs;

import java.util.List;

import com.example.java_demo_test.entity.Login;
import com.example.java_demo_test.vo.LoginResponse;

public interface LoginService {

	public LoginResponse addInfo(List<Login> login);
	
	public LoginResponse activeAccount(List<Login> login);
	
	public LoginResponse loginAccount(List<Login> login);
	
	public LoginResponse findByCityContainingOrderByAgeAsc(String str);
	
}
