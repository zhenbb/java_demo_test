package com.example.java_demo_test.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.Register;
import com.example.java_demo_test.vo.RegisterResponse;

@Repository
public interface RegisterDAO extends JpaRepository<Register, String> {

	public Register findByAccountAndPwd(String account , String pwd);
	
	public Register findByAccountAndPwdAndActive(String account , String pwd , boolean active);
}
