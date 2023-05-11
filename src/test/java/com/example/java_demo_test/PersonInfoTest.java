package com.example.java_demo_test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.respository.PersonInfoDAO;
import com.example.java_demo_test.service.ifs.PersonInfoService;

@SpringBootTest
public class PersonInfoTest {
	
	@Autowired
	private PersonInfoDAO personInfoDao;
	
	@Autowired
	private PersonInfoService personInfoService;

	
	@Test
	public void updateNameByIdTest() {
		int result = personInfoDao.updateNameById("123456","AAAAA",12,"台北市");
		System.out.println(result);
	}
	//如果我原本的DB中有這筆資料,會回傳1,並修改這筆變成這個資料內容
	//如果是沒有在DB的資料,會回傳0,並且不會新增資料或異動
	
	@Test
	public void doQueryByAgeTest() {
		 List<PersonInfo>res = personInfoDao.doQueryByAge(20);
		 System.out.println(res.size());
	}
	
	@Test
	public void doUpdateAgeByNameTest() {
		int res =personInfoDao.doUpdateAgeByName(38 , "AAAAA");
		System.out.println(res);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//JPA containing用法
	@Test
	public void findByNameOrCityContaining() {
		List<PersonInfo> a = personInfoDao.findByNameContainingOrCityContaining("", "");
		List<PersonInfo> b = personInfoDao.findByNameContainingOrCityContaining(null, null);
		List<PersonInfo> c = personInfoDao.findByNameContainingOrCityContaining("A", "");
		System.out.println(a.size());
		System.out.println(b.size());
		System.out.println(c.size());
	}
	
	
	//Query用法
	@Test
	public void queryAllTest() {
		String name = "";
		String city = "";
		List<PersonInfo> res = personInfoService.getAll(name,city);
		System.out.println(res.size());
		System.out.println("=============");
		name="A";
		city="";
		List<PersonInfo> res1 = personInfoService.getAll(name,city);
		System.out.println(res1.size());
		System.out.println("=============");
		name="";
		city="市";
		List<PersonInfo> res2 = personInfoService.getAll(name,city);
		System.out.println(res2.size());
		System.out.println("=============");
		name="A";
		city="市";
		List<PersonInfo> res3 = personInfoService.getAll(name,city);
		System.out.println(res3.size());
		
	}
	
	@Test
	public void queryNothingTest() {
			String name = "";
			String city = "";
			List<PersonInfo> res = personInfoService.getNothing(name,city);
			System.out.println(res.size());
			System.out.println("=============");
			name="A";
			city="";
			List<PersonInfo> res1 = personInfoService.getNothing(name,city);
			System.out.println(res1.size());
			System.out.println("=============");
			name="";
			city="市";
			List<PersonInfo> res2 = personInfoService.getNothing(name,city);
			System.out.println(res2.size());
			System.out.println("=============");
			name="A";
			city="市";
			List<PersonInfo> res3 = personInfoService.getNothing(name,city);
			System.out.println(res3.size());

	}
	
	
}


