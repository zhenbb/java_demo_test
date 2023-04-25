package com.example.java_demo_test.vo;

import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.Menu;

public class OrderResponse {
	
	private List<Menu> menus;
	
	private Map<String , Integer> orderMap;
	

	
	private int totalPrice;
	
	private String messege;
	
	

	public OrderResponse() {
		super();

	}
	

	public OrderResponse(String messege) {
		super();
		this.messege = messege;
	}

	

	public OrderResponse(List<Menu> menus, String messege) {
		super();
		this.menus = menus;
		this.messege = messege;
	}


	public OrderResponse(Map<String, Integer> orderMap, int totalPrice, String messege) {
		super();
		this.orderMap = orderMap;
		this.totalPrice = totalPrice;
		this.messege = messege;
	}


	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public String getMessege() {
		return messege;
	}

	public void setMessege(String messege) {
		this.messege = messege;
	}


	public int getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}


	public Map<String, Integer> getOrderMap() {
		return orderMap;
	}


	public void setOrderMap(Map<String, Integer> orderMap) {
		this.orderMap = orderMap;
	}
	
	
	
	
}
