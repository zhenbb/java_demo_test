package com.example.java_demo_test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "menu")   //���Ʈw�۲Ū���Ƨ��W��
public class Menu {
	
	@Id          //�D��(PK),�ҥH�n�⥦�]�@��Id
	@Column(name = "name") //���Ʈw�������ۦP���W�٦��p
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
