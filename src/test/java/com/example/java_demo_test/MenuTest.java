package com.example.java_demo_test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.respository.MenuDAO;
import com.example.java_demo_test.service.ifs.OrderService;
import com.example.java_demo_test.vo.OrderResponse;



@SpringBootTest
public class MenuTest {
	
	//@Autowired¤¶­±,¥L·|¦Û¤v±a¹ê§@
	@Autowired
	private MenuDAO menuDao;
	
	@Autowired
	private OrderService orderService;
	
	
	@Test
	public void addMenuInfo() {
		Menu menu = new Menu("¤û", 100);
		menuDao.save(menu);
		
		Menu menu1 = new Menu ("Âû", 200);
		menuDao.save(menu1);
		
		Menu menu2 = new Menu ("½Þ", 300);
		menuDao.save(menu2);
		
	}
	
	
//	@Test
//	public void addMenuTest() {
//		List<Menu>list = new ArrayList<>();
//		OrderResponse response = orderService.addMenus(list);
//		List<Menu> responseList = response.getMenus();
//		Assert.isTrue(responseList !=null , "·s¼WÀ\ÂI¿ù»~");
//	}
//	
	
	
	@Test
	public void MenuTest() {
		
		List<Menu>menus1 = new ArrayList<>();
		menus1.add(new Menu("", 100));
		menus1.add(new Menu("«¢«¢", 200));
		menus1.add(new Menu("«¢«¢«¢",300));
		//{"", «¢«¢, «¢«¢«¢}
		
		//Menu m = new Menu("X",10);
		orderService.addMenus(menus1);
		//menuDao.delete(m);
	}
	
	@Test
	public void orderTest() {
		
		Map <String , Integer> order = new HashMap<>();
		order.put("¤û", 1);
		order.put("Âû", 3);
		order.put("½Þ", 20);
		
		orderService.order(order);
	}
	
//	@Test
//	public void order1Test() {
//		List<String> order1 = new ArrayList<>();
//		order1.add("¤û");
//		order1.add("AAA");
//		order1.add("Âû");
//		order1.add("BBB");
//		
//		OrderResponse res = orderService.order1(order1);
//		System.out.println("123123");
//		System.out.println(res.getMessege());
//		
//	}
//	
//	@Test
//	public void order2Test() {
//		List<Integer> order2 = new ArrayList<>();
//		order2.add(1);
//		order2.add(2);
//		order2.add(0);
//		order2.add(3);
		
		
		
		@Test
		public void orderTest1() {
			
			Map <String , Integer> order = new HashMap<>();
			order.put("¤û", 1);
			order.put("AAA", 3);
			order.put("BBB", 3);
			order.put("½Þ", 0);
			
			OrderResponse res =orderService.order1(order);
			System.out.println(res.getOrderMap()+"    "+res.getTotalPrice()+"    "+res.getMessege());
		}
		
	


}
