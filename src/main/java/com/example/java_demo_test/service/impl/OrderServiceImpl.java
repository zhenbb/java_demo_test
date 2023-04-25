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
		if(CollectionUtils.isEmpty(menus)) { //���P��(menus == null || menus.isEmpty())
			return new OrderResponse("�s�W�\�I���~");
		}
		for(Menu menuItem : menus) { //�p�G�O�Ū��}�C,��for�j�駹�����|�i��,�ҥH�n�ˬd�}�C�O�_���Ű}�C(menus.isEmpty())
			if(!StringUtils.hasText(menuItem.getName())) {
				return new OrderResponse("�\�I�W�٤��ର��");
			}
			if(menuItem.getPrice()<= 0 ){
				return new OrderResponse("�\�I������~");
			}
		}
//		for(Menu menuItem : menus) {
//			menuDao.save(menus);
//		}�浧�浧���s�ܯӸ귽,����ĳ�Φ��g�k
		List<Menu>response = menuDao.saveAll(menus);
		return new OrderResponse(response , "�s�W�\�I���\");
	}
		


	@Override
	public OrderResponse order(Map<String, Integer> orderMap) {
		List<String> itemList =new ArrayList<>();
		for(Entry<String , Integer>item : orderMap.entrySet()) {
			if(item.getValue()<0) {
				return new OrderResponse("�\�I�ƶq���~");
			}
			itemList.add(item.getKey());
		}	
		
		List<Menu> result = menuDao.findAllById(itemList);
		int totalPrice = 0;
		Map<String , Integer> finalOrderMap = new HashMap<>();
		for(Menu menu: result) {
			String item = menu.getName(); //�\�I�W��
			int price = menu.getPrice();
			for(Entry<String , Integer>map : orderMap.entrySet()) {
					String key = map.getKey();
					int value = map.getValue();
					if(item .equals(key)) {
					int singalTotalPrice = price *value ;  //��Ӳ��~���`��
					totalPrice +=  singalTotalPrice;
					finalOrderMap.put(key, value);
					}
			}
		}
		totalPrice = totalPrice>500 ? (int)(totalPrice *0.9) :totalPrice;
		return new OrderResponse(finalOrderMap , totalPrice ,"�I�\���\" );
	}


	@Override
	public OrderResponse order1(Map<String, Integer> orderMap) {
		String message = "";
		List<String> itemList =new ArrayList<>();
		for(Entry<String , Integer>item : orderMap.entrySet()) {
			if(!menuDao.existsById(item.getKey())) {
				message += "�\�I�W��"+item.getKey()+ "���~  ";
			}else if(item.getValue()<1) {
				message +="�\�I" + item.getKey()+ "�ƶq���~  ";
			}else {
				itemList.add(item.getKey());
			}
		}	

		List<Menu> result = menuDao.findAllById(itemList);
		int totalPrice = 0;
		Map<String , Integer> finalOrderMap = new HashMap<>();
		for(Menu menu: result) {
			String item = menu.getName(); //�\�I�W��
			int price = menu.getPrice();
			
			for(Entry<String , Integer>map : orderMap.entrySet()) {
					String key = map.getKey();
					int value = map.getValue();//����
					if(item .equals(key)) {
					int singalTotalPrice = price *value ;  //��Ӳ��~���`��
					totalPrice +=  singalTotalPrice;
					finalOrderMap.put(key, value);
					}
			}
		}
		totalPrice = totalPrice>500 ? (int)(totalPrice *0.9) :totalPrice;
		return new OrderResponse(finalOrderMap , totalPrice , message + "�I�\���\" );
	}
	
	@Override
	public OrderResponse getAllMenus() {

		return null;
	}

	
	@Override
	public MenuResponse getMenuByName(String name) {
		if(!StringUtils.hasText(name)) {
			return new MenuResponse("�\�I�W�ٿ��~");
		}
		Optional<Menu> op = menuDao.findById(name);
		if(!op.isPresent()) {
			return new MenuResponse("�\�I���s�b");
		}
		return new MenuResponse(op.get(),"���\");
	}


//	@Override
//	public MenuResponse getMenuByName(String name) {
////			if(!StringUtils.hasText(name)) {
////				return new MenuResponse("�\�I�W�٤��ର��");
////			}
//		
//		if(name == null || name=="") {
//			return new MenuResponse("�\�I�W�ٿ��~");
//		}
//		
//		List<Menu> list_menu  = new ArrayList<>();
//		list_menu.add(menuDao.findById(name).get());
//		
//		return new MenuResponse(list_menu, "�d�ߦ��\");
//	}	
	
//	@Override
//	public OrderResponse updateMenu(List<Menu> menus) {
//		List<Menu> newList = new ArrayList<>();
//		String f = newList.get() ;
//		int p;
//		//�u��ק�w�s�b��������(���椣�o���t��)
//		//���s�b����椣��s�W
//		//��^�ק�᪺���W��+�s����
//		for(Menu m : menus ) {
//			String item = m.getName(); 
//			if(!item.contains(f) || CollectionUtils.isEmpty(menus )) {
//				return new OrderResponse("�d�L�����");
//			}
//			p=m.getPrice();
//			if(p<=0) {
//				return new OrderResponse("���椣�o���t��");
//			}newList.add(menus.getName());
//		}
//		return null;
//	}

	@Override
	//�Ѯv���g�k
	//�u��ק�w�s�b��������(���椣�o���t��)
	//���s�b����椣��s�W
	//��^�ק�᪺���W��+�s����
	public OrderResponse updateMenu(List<Menu> menus) {//menus�O�ϥΪ̿�J�����,���]�ڭn�קאּ��500 AAA 300 �� 600
		List<Menu>updateMenu = new ArrayList<>();
//		for(Menu menu : menus) {
//			boolean bool = menuDao.existsById(menu.getName());//exist�P�_���S���s�b,�I�\�t��OK,��F�ưȩ�NO�����A�X(�ӯӸ귽,�n�H��Ʈw���Ƥj�p�ӧP�_)
//			if(menuDao.existsById(menu.getName())) {
//				updateMenu.add(menu);
//			}
//		}
//		if(updateMenu.size() == 0) {
//			return new OrderResponse("���榡���~");
//		}
//		return new OrderResponse(menuDao.saveAll(updateMenu),"���\");
		

		if(CollectionUtils.isEmpty(menus)) {//�ˬd��J���F��O�_��null�Ϊ�
			return new OrderResponse("���榡���~");
		}
		List<String>ids = new ArrayList<>();
		for(Menu menu:menus) {
			if(menu.getPrice() <0) {
				return new OrderResponse("��������~");
			}
			ids.add(menu.getName()); //�ثe�o��ids=["��","AAA","��"]
		}
	
		List<Menu>res = menuDao.findAllById(ids);//�P�_��Ʈw�ح����S���o��id,�o�����ӥu�|����ⵧ(�S��AAA) ("��"100 ,"��"200)
		if(res.size() ==0) {//�P�_�o�ӧ�X�Ӫ��Ȧ��S�����e
			return new OrderResponse("�d�L���");
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
		return new OrderResponse(menuDao.saveAll(newMenuList),"���\");
	}



	
	
	
		
}

