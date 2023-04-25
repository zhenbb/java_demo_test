package com.example.java_demo_test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "menu")   //跟資料庫相符的資料夾名稱
public class Menu {
	
	@Id          //主鍵(PK),所以要把它設一個Id
	@Column(name = "name") //跟資料庫內的欄位相同的名稱串聯
	private String name;

	@Column(name = "price")
	private int price;

	public Menu() {
		super();

	}
	

	public Menu(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	



}
