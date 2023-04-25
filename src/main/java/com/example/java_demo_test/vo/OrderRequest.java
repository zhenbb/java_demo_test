package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.Menu;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderRequest {
	
	@JsonProperty("menu_list") //改寫名稱(要記得先讓原本的程式停止重新啟動)
	List<Menu> menus;

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
	
	
	@JsonProperty("name")
	String name;
	
//	private String name1;
//
//	public String getName1() {
//		return name1;
//	}
//
//	public void setName1(String name1) {
//		this.name1 = name1;
//	}
	
	public String getMenuByName() {
		return name;
	}
}
