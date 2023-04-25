package com.example.java_demo_test.service.ifs;

import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.vo.MenuResponse;
import com.example.java_demo_test.vo.OrderResponse;

public interface OrderService {
	
	public MenuResponse getMenuByName(String name); //name = 餐點名稱
	
	public OrderResponse getAllMenus();
	
	public OrderResponse addMenus(List<Menu> menus);
	//介面方法因為要被實作,要寫public,不然就是不寫型態
	public OrderResponse order(Map<String , Integer> order);
	
	public OrderResponse updateMenu(List<Menu> menus);

	public OrderResponse order1(Map<String, Integer> orderMap);

}