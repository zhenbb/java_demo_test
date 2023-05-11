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
		int result = personInfoDao.updateNameById("123456","AAAAA",12,"�x�_��");
		System.out.println(result);
	}
	//�p�G�ڭ쥻��DB�����o�����,�|�^��1,�íק�o���ܦ��o�Ӹ�Ƥ��e
	//�p�G�O�S���bDB�����,�|�^��0,�åB���|�s�W��Ʃβ���
	
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//JPA containing�Ϊk
	@Test
	public void findByNameOrCityContaining() {
		List<PersonInfo> a = personInfoDao.findByNameContainingOrCityContaining("", "");
		List<PersonInfo> b = personInfoDao.findByNameContainingOrCityContaining(null, null);
		List<PersonInfo> c = personInfoDao.findByNameContainingOrCityContaining("A", "");
		System.out.println(a.size());
		System.out.println(b.size());
		System.out.println(c.size());
	}
	
	
	//Query�Ϊk
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
		city="��";
		List<PersonInfo> res2 = personInfoService.getAll(name,city);
		System.out.println(res2.size());
		System.out.println("=============");
		name="A";
		city="��";
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
			city="��";
			List<PersonInfo> res2 = personInfoService.getNothing(name,city);
			System.out.println(res2.size());
			System.out.println("=============");
			name="A";
			city="��";
			List<PersonInfo> res3 = personInfoService.getNothing(name,city);
			System.out.println(res3.size());

	}
	
	
}


