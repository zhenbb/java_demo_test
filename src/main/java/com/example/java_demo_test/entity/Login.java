package com.example.java_demo_test.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login")
public class Login {
	
	@Id
	@Column(name = "account")
	private String account;
	@Column(name = "password")
	private String password;
	@Column(name = "name")
	private String name;
	@Column(name = "age")
	private int age;
	@Column(name = "city")
	private String city;
	@Column(name = "register_time")
	private LocalDateTime registerTime;
	//LocalDateTime.now();
	@Column(name = "active")
	private boolean isActive;
	
//	private List<login> loginList = new ArrayList<>(account,name,age, city,registerTime, isActive);
	
	
	public Login() {
		super();

	}
	
	
	public Login(String account, String password, String name) {
		super();
		this.account = account;
		this.password = password;
		this.name = name;
	}
	

	public Login(String account, String password, String name, int age, String city, LocalDateTime registerTime,
			boolean isActive) {
		super();
		this.account = account;
		this.password = password;
		this.name = name;
		this.age = age;
		this.city = city;
		this.registerTime = registerTime;
		this.isActive = isActive;
	}


	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return password;
	}
	public void setPwd(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public LocalDateTime getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(LocalDateTime registerTime) {
		this.registerTime = registerTime;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public static Login addInfo(List<Login> login) {

		return null;
	}
	
	
	
	
}
