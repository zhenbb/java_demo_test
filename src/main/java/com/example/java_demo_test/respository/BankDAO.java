package com.example.java_demo_test.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.Bank;
//DAO:Data Access Object ��ƳB�z����

@Repository
public interface BankDAO extends JpaRepository<Bank, String> {
	
	public Bank findByAccountAndPwd(String account , String pwd);

}
