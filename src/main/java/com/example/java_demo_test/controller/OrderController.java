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
	
	@PostMapping(value = "add_menus") //�o�Ӻ٬�API �N�O�@�����ε{������,��POSTMAN(�{��)���F������A�Ȩ�o(�u��value�o)(value=�J�f�I,�n��url�@�P)
	public OrderResponse addMenus(@RequestBody OrderRequest request) { // ���F��postman���F��ǰe�i�Ӫ��t���@RequestBody,���L�নJSON�榡
		return orderService.addMenus(request.getMenus());
	}
	
	@PostMapping(value = "get_menu_byName")
	public MenuResponse getMenuByName(@RequestBody OrderRequest request) {
		return orderService.getMenuByName(request.getMenuByName());
		//return orderService.getMenuByName(request.getName1());
	}
	
}
