package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.Menu;

public class MenuResponse {
	
//	private List<Menu> menus;
	private Menu menus;
	private String messege;
	
//	public List<Menu> getMenus() {
//		return menus;
//	}
//
//	public void setMenus(List<Menu> menus) {
//		this.menus = menus;
//	}

	public Menu getMenus() {
		return menus;
	}

	public void setMenus(Menu menus) {
		this.menus = menus;
	}

	public String getMessege() {
		return messege;
	}

	public void setMessege(String messege) {
		this.messege = messege;
	}

	
	
	public MenuResponse() {

	}


	public MenuResponse(String messege) {
		super();
		this.messege = messege;
	}

	public MenuResponse(Menu menus, String messege) {
		super();
		this.menus = menus;
		this.messege = messege;
	}


}
