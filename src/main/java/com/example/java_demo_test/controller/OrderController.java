package com.example.java_demo_test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.service.ifs.OrderService;
import com.example.java_demo_test.vo.MenuResponse;
import com.example.java_demo_test.vo.OrderRequest;
import com.example.java_demo_test.vo.OrderResponse;

@RestController
public class OrderController {
	
	@Autowired
	public OrderService orderService;
	
	@GetMapping(value = "get_all_menus")
	public OrderResponse GetAllMenus() {
		return orderService.getAllMenus();
	}
	
	@PostMapping(value = "add_menus") //這個稱為API 就是一個應用程式介面,讓POSTMAN(程式)的東西對應服務到這(只有value這)(value=入口點,要跟url一致)
	public OrderResponse addMenus(@RequestBody OrderRequest request) { // 為了讓postman的東西傳送進來的配對用@RequestBody,讓他轉成JSON格式
		return orderService.addMenus(request.getMenus());
	}
	
	@PostMapping(value = "get_menu_byName")
	public MenuResponse getMenuByName(@RequestBody OrderRequest request) {
		return orderService.getMenuByName(request.getMenuByName());
		//return orderService.getMenuByName(request.getName1());
	}
	
}
