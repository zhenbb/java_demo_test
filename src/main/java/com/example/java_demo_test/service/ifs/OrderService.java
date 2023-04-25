package com.example.java_demo_test.service.ifs;

import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.vo.MenuResponse;
import com.example.java_demo_test.vo.OrderResponse;

public interface OrderService {
	
	public MenuResponse getMenuByName(String name); //name = �\�I�W��
	
	public OrderResponse getAllMenus();
	
	public OrderResponse addMenus(List<Menu> menus);
	//������k�]���n�Q��@,�n�gpublic,���M�N�O���g���A
	public OrderResponse order(Map<String , Integer> order);
	
	public OrderResponse updateMenu(List<Menu> menus);

	public OrderResponse order1(Map<String, Integer> orderMap);

}