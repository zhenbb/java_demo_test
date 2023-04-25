package com.example.java_demo_test.vo;

import com.example.java_demo_test.entity.Bank;

//Response ¦^À³
public class BankResponse {

	private Bank bank ; 
	
	private String messege ;
	

	public BankResponse() {
	}

	public BankResponse(Bank bank, String messege) {
		super();
		this.bank = bank;
		this.messege = messege;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public String getMessege() {
		return messege;
	}

	public void setMessege(String messege) {
		this.messege = messege;
	}


	
	
}
