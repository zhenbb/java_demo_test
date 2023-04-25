package com.example.java_demo_test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//persistence=資料持久化

@Entity
@Table(name = "bank")   //跟資料庫相符的資料夾名稱
public class Bank {
	
	@Id           //因為account是主鍵(PK),所以要把它設一個Id
	@Column(name = "account") //跟資料庫內的欄位相同的名稱串聯
	private String account;
	@Column(name = "pwd")
	private String pwd;
	@Column(name = "amount")
	private int amount;
	

	public Bank() {

	}

	public Bank(String account, String pwd, int amount) {
		super();
		this.account = account;
		this.pwd = pwd;
		this.amount = amount;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
