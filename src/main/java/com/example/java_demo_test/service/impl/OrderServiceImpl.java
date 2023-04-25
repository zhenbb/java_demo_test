package com.example.java_demo_test.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.respository.MenuDAO;
import com.example.java_demo_test.service.ifs.OrderService;
import com.example.java_demo_test.vo.MenuResponse;
import com.example.java_demo_test.vo.OrderResponse;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private MenuDAO menuDao;

	@Override
	public OrderResponse addMenus(List<Menu> menus) {
		if(CollectionUtils.isEmpty(menus)) { //等同於(menus == null || menus.isEmpty())
			return new OrderResponse("新增餐點錯誤");
		}
		for(Menu menuItem : menus) { //如果是空的陣列,此for迴圈完全不會進來,所以要檢查陣列是否為空陣列(menus.isEmpty())
			if(!StringUtils.hasText(menuItem.getName())) {
				return new OrderResponse("餐點名稱不能為空");
			}
			if(menuItem.getPrice()<= 0 ){
				return new OrderResponse("餐點價格錯誤");
			}
		}
//		for(Menu menuItem : menus) {
//			menuDao.save(menus);
//		}單筆單筆的存很耗資源,不建議用此寫法
		List<Menu>response = menuDao.saveAll(menus);
		return new OrderResponse(response , "新增餐點成功");
	}
		


	@Override
	public OrderResponse order(Map<String, Integer> orderMap) {
		List<String> itemList =new ArrayList<>();
		for(Entry<String , Integer>item : orderMap.entrySet()) {
			if(item.getValue()<0) {
				return new OrderResponse("餐點數量錯誤");
			}
			itemList.add(item.getKey());
		}	
		
		List<Menu> result = menuDao.findAllById(itemList);
		int totalPrice = 0;
		Map<String , Integer> finalOrderMap = new HashMap<>();
		for(Menu menu: result) {
			String item = menu.getName(); //餐點名稱
			int price = menu.getPrice();
			for(Entry<String , Integer>map : orderMap.entrySet()) {
					String key = map.getKey();
					int value = map.getValue();
					if(item .equals(key)) {
					int singalTotalPrice = price *value ;  //單個產品的總價
					totalPrice +=  singalTotalPrice;
					finalOrderMap.put(key, value);
					}
			}
		}
		totalPrice = totalPrice>500 ? (int)(totalPrice *0.9) :totalPrice;
		return new OrderResponse(finalOrderMap , totalPrice ,"點餐成功" );
	}


	@Override
	public OrderResponse order1(Map<String, Integer> orderMap) {
		String message = "";
		List<String> itemList =new ArrayList<>();
		for(Entry<String , Integer>item : orderMap.entrySet()) {
			if(!menuDao.existsById(item.getKey())) {
				message += "餐點名稱"+item.getKey()+ "錯誤  ";
			}else if(item.getValue()<1) {
				message +="餐點" + item.getKey()+ "數量錯誤  ";
			}else {
				itemList.add(item.getKey());
			}
		}	

		List<Menu> result = menuDao.findAllById(itemList);
		int totalPrice = 0;
		Map<String , Integer> finalOrderMap = new HashMap<>();
		for(Menu menu: result) {
			String item = menu.getName(); //餐點名稱
			int price = menu.getPrice();
			
			for(Entry<String , Integer>map : orderMap.entrySet()) {
					String key = map.getKey();
					int value = map.getValue();//份數
					if(item .equals(key)) {
					int singalTotalPrice = price *value ;  //單個產品的總價
					totalPrice +=  singalTotalPrice;
					finalOrderMap.put(key, value);
					}
			}
		}
		totalPrice = totalPrice>500 ? (int)(totalPrice *0.9) :totalPrice;
		return new OrderResponse(finalOrderMap , totalPrice , message + "點餐成功" );
	}
	
	@Override
	public OrderResponse getAllMenus() {

		return null;
	}

	
	@Override
	public MenuResponse getMenuByName(String name) {
		if(!StringUtils.hasText(name)) {
			return new MenuResponse("餐點名稱錯誤");
		}
		Optional<Menu> op = menuDao.findById(name);
		if(!op.isPresent()) {
			return new MenuResponse("餐點不存在");
		}
		return new MenuResponse(op.get(),"成功");
	}


//	@Override
//	public MenuResponse getMenuByName(String name) {
////			if(!StringUtils.hasText(name)) {
////				return new MenuResponse("餐點名稱不能為空");
////			}
//		
//		if(name == null || name=="") {
//			return new MenuResponse("餐點名稱錯誤");
//		}
//		
//		List<Menu> list_menu  = new ArrayList<>();
//		list_menu.add(menuDao.findById(name).get());
//		
//		return new MenuResponse(list_menu, "查詢成功");
//	}	
	
//	@Override
//	public OrderResponse updateMenu(List<Menu> menus) {
//		List<Menu> newList = new ArrayList<>();
//		String f = newList.get() ;
//		int p;
//		//只能修改已存在的菜單價格(價格不得為負數)
//		//不存在的菜單不能新增
//		//返回修改後的菜單名稱+新價格
//		for(Menu m : menus ) {
//			String item = m.getName(); 
//			if(!item.contains(f) || CollectionUtils.isEmpty(menus )) {
//				return new OrderResponse("查無此菜單");
//			}
//			p=m.getPrice();
//			if(p<=0) {
//				return new OrderResponse("價格不得為負數");
//			}newList.add(menus.getName());
//		}
//		return null;
//	}

	@Override
	//老師的寫法
	//只能修改已存在的菜單價格(價格不得為負數)
	//不存在的菜單不能新增
	//返回修改後的菜單名稱+新價格
	public OrderResponse updateMenu(List<Menu> menus) {//menus是使用者輸入的菜單,假設我要修改為牛500 AAA 300 雞 600
		List<Menu>updateMenu = new ArrayList<>();
//		for(Menu menu : menus) {
//			boolean bool = menuDao.existsById(menu.getName());//exist判斷有沒有存在,點餐系統OK,戶政事務所NO較不適合(太耗資源,要以資料庫筆數大小來判斷)
//			if(menuDao.existsById(menu.getName())) {
//				updateMenu.add(menu);
//			}
//		}
//		if(updateMenu.size() == 0) {
//			return new OrderResponse("菜單格式錯誤");
//		}
//		return new OrderResponse(menuDao.saveAll(updateMenu),"成功");
		

		if(CollectionUtils.isEmpty(menus)) {//檢查輸入的東西是否為null或空
			return new OrderResponse("菜單格式錯誤");
		}
		List<String>ids = new ArrayList<>();
		for(Menu menu:menus) {
			if(menu.getPrice() <0) {
				return new OrderResponse("菜單價格錯誤");
			}
			ids.add(menu.getName()); //目前這個ids=["牛","AAA","雞"]
		}
	
		List<Menu>res = menuDao.findAllById(ids);//判斷資料庫裏面有沒有這些id,這裡應該只會撈到兩筆(沒有AAA) ("牛"100 ,"雞"200)
		if(res.size() ==0) {//判斷這個找出來的值有沒有內容
			return new OrderResponse("查無菜單");
		}
		List<Menu>newMenuList = new ArrayList<>();
		for(Menu resmenu : res) {
			String itemInDB = resmenu.getName();
			for(Menu menu : menus) {
				String updateItem = menu.getName();
				if(itemInDB.equals(updateItem)) {
					newMenuList.add(menu);
					}
			}
		}
		return new OrderResponse(menuDao.saveAll(newMenuList),"成功");
	}



	
	
	
		
}

